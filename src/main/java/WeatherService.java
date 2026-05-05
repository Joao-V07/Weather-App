import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    GeocodingService GC_service = new GeocodingService();
    double[] coords = GC_service.APIGCRequest("Sydney");
    double lat = coords[0];
    double lon = coords[1];
    HttpClient client = HttpClient.newHttpClient();
    public void APIRequest(){
        String url = buildUrl();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.println(body);
        } catch(IOException | InterruptedException e){

        }
    }
    public String buildUrl(){
        String apiKey = System.getenv("WEATHER_API_KEY");
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
                + "&lon=" + lon + "&units=metric&appid=" + apiKey;
        return url;
    }
}
