import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    GeocodingService GC_service = new GeocodingService();
    HttpClient client = HttpClient.newHttpClient();
    public void APIRequest(String city, String country){
        double[] coords = GC_service.APIGCRequest(city, country);
        double lat = coords[0];
        double lon = coords[1];
        String url = buildUrl(lat, lon);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.println(body);
        } catch(IOException | InterruptedException e){
            System.out.println("api do clima deu certo nao");
        }
    }
    public String buildUrl(Double lat, Double lon){
        String apiKey = System.getenv("WEATHER_API_KEY");
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
                + "&lon=" + lon + "&units=metric&appid=" + apiKey;
        return url;
    }
}
