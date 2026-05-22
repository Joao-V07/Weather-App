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

    public GeoResult APIGCRequest(String city, String countryCode) {
        String url = BuildURL(city, countryCode);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            checkStatusCode(response);
            String body = response.body();
            JsonArray array = JsonParser.parseString(body).getAsJsonArray();
            if (array.isEmpty()) throw new ExceptionHandling("City not found, please check your city name and try again.");
            String cityName = array.get(0).getAsJsonObject().get("name").getAsString();
            String countryName = array.get(0).getAsJsonObject().get("country").getAsString();
            double lat = array.get(0).getAsJsonObject().get("lat").getAsDouble();
            double lon = array.get(0).getAsJsonObject().get("lon").getAsDouble();
            return new GeoResult(cityName, countryName, lat, lon);
        } catch (InterruptedException e) {
            throw new ExceptionHandling("Connection interrupted, please try again.");
        }
        catch (IOException e) {
            throw new ExceptionHandling("There was a connection error, please check your internet and try again.");
        }
    }

    private String BuildURL(String city, String country){
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city
                     + "," + country;
        url += "&limit=5&appid=" + apiKey;
        return url;
    }

    public void checkStatusCode(HttpResponse<String> response){
        int statusCode = response.statusCode();
        if (statusCode == 401)throw new ExceptionHandling("There was an error with the App, please contact the administrator.");
        if (statusCode == 429)throw new ExceptionHandling("There is too many data requests at the moment, please try again later.");
        if (statusCode == 500 || statusCode == 503)throw new ExceptionHandling("There was a problem with the weather service, please try again later.");
        if (statusCode == 502 || statusCode == 504)throw new ExceptionHandling("There was a problem with the servers, please try again later.");
    }
}
