import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import models.GeoResult;

public class GeocodingService {
    String apiKey = System.getenv("GEO_CODING_KEY");
    HttpClient client = HttpClient.newHttpClient();

    /**
     * Makes the Geocoding API's request and assigns necessary data into variables.
     * @param city City name provided by the user.
     * @param countryCode Country code provided by the "countryNameToCode" method.
     * @return Location's city and country's name and coordinates(e.g. São Paulo, Brazil, -23.5475, -46.6361)
     */
    public GeoResult APIGCRequest(String city, String countryCode) {
        String url = BuildURL(city, countryCode);
        // Builds the API request.
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            // Sends the API request and assigns its response to a variable.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Checks what is the API's status code and throws an exception if there was a problem.
            checkStatusCode(response);
            String body = response.body();
            // Navigates through the API's response and extracts necessary data,
            // and checks if it's empty.
            JsonArray array = JsonParser.parseString(body).getAsJsonArray();
            if (array.isEmpty()) throw new ExceptionHandling("City not found, please check your city name and try again.");
            String cityName = array.get(0).getAsJsonObject().get("name").getAsString();
            String countryName = array.get(0).getAsJsonObject().get("country").getAsString();
            double lat = array.get(0).getAsJsonObject().get("lat").getAsDouble();
            double lon = array.get(0).getAsJsonObject().get("lon").getAsDouble();
            return new GeoResult(cityName, countryName, lat, lon);
          // Catches any connection or interruption error.
        } catch (InterruptedException e) {
            throw new ExceptionHandling("the connection was interrupted, please try again.");
        }
        catch (IOException e) {
            throw new ExceptionHandling("There was a connection error, please check your internet and try again.");
        }
    }

    /**
     * Builds the API's URL to make the coordinates request.
     * @param city City name provided by the user.
     * @param country COuntry name provided by the user.
     * @return API's URL.
     */
    private String BuildURL(String city, String country){
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city
                     + "," + country;
        url += "&limit=5&appid=" + apiKey;
        return url;
    }

    /**
     * Throws assigned exceptions based on the API's status code.
     * @param response API's response.
     */
    public void checkStatusCode(HttpResponse<String> response){
        int statusCode = response.statusCode();
        if (statusCode == 401)throw new ExceptionHandling("There was an error with the App, please contact the administrator.");
        if (statusCode == 429)throw new ExceptionHandling("There is too many data requests at the moment, please try again later.");
        if (statusCode == 500 || statusCode == 503)throw new ExceptionHandling("There was a problem with the weather service, please try again later.");
        if (statusCode == 502 || statusCode == 504)throw new ExceptionHandling("There was a problem with the servers, please try again later.");
    }
}
