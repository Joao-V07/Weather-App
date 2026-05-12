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
    public WeatherData APIRequest(double lat, double lon){
        String url = buildUrl(lat, lon);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.println(body);
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
            String city = json.get("name").getAsString();
            String country = json.get("sys").getAsJsonObject().get("country").getAsString();
            return new WeatherData(weatherCondition, weatherDescription, temp, feelsLike, humidity, windSpeed, windDir, cloudiness, city, country);
        } catch(IOException | InterruptedException e){
            System.out.println("api do clima deu certo nao");
            return null;
        }
    }
    public String buildUrl(Double lat, Double lon){
        String apiKey = System.getenv("WEATHER_API_KEY");
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
                + "&lon=" + lon + "&units=metric&appid=" + apiKey;
    }
}
