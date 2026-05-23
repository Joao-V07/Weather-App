import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    HttpClient client = HttpClient.newHttpClient();

    /**
     * makes the Weather API request and assigns the necessary data into variables.
     * @param lat Location's latitude as Double (e.g.-33.928969).
     * @param lon Location's longitude as Double (e.g. 151.147410).
     * @param city City name given by the Geocoding API (e.g. London)
     * @param country Country code given by the Geocoding API (e.g. UK)
     * @return Weather data given by the Weather API (e.g. location's temperature, humidity)
     */
    public WeatherData APIRequest(double lat, double lon, String city, String country){
        String url = buildUrl(lat, lon);
        // Creates the API request using the URL given by the "buildURL" method.
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try{
            // Sends the API request and assigns its response as a string to a variable.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            GeocodingService geo = new GeocodingService();
            // Checks what is the API status code and throws an exception if there was a problem.
            geo.checkStatusCode(response);
            String body = response.body();
            // Access the JSON response to assign variables to extract weather data.
            // Fields inside "main", "wind" and "cloud" are accessed as Json objects,
            // while "weather" is an array, so get(0) is used to access its first element.
            JsonObject json = JsonParser.parseString(body).getAsJsonObject();
            JsonObject main = json.get("main").getAsJsonObject();
            String weatherCondition = json.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
            String weatherDescription = json.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
            int temp = main.get("temp").getAsInt();
            int feelsLike = main.get("feels_like").getAsInt();
            int humidity = main.get("humidity").getAsInt();
            int windSpeed = json.get("wind").getAsJsonObject().get("speed").getAsInt();
            int windDir = json.get("wind").getAsJsonObject().get("deg").getAsInt();
            int cloudiness = json.get("clouds").getAsJsonObject().get("all").getAsInt();
            String code = String.valueOf(response.statusCode());
            return new WeatherData(weatherCondition, weatherDescription, temp, feelsLike, humidity, windSpeed, windDir, cloudiness, city, country, code);
          // catches any connection or interruption error.
        } catch(IOException e){
            throw new ExceptionHandling("There was a connection error, please check your internet and try again.");
        } catch(InterruptedException e){
            throw new ExceptionHandling("The connection was interrupted, please try again.");
        }
    }

    /**
     * Builds the API's URL to make the weather data request
     * @param lat Location's latitude as Double (e.g.-33.928969)
     * @param lon Location's longitude as Double (e.g. 151.147410)
     * @return the API's URL as String.
     */
    public String buildUrl(Double lat, Double lon){
        String apiKey = System.getenv("WEATHER_API_KEY");
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
                + "&lon=" + lon + "&units=metric&appid=" + apiKey;
    }
}
