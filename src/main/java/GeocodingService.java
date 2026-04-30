import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GeocodingService {
    String apiKey = System.getenv("GEO_CODING_KEY");
    HttpClient client = HttpClient.newHttpClient();
public void APIGCRequest(String city){
    APIGCRequest(city, null);
}
    public String APIGCRequest(String city, String countryCode) {
        String url = BuildURL(city, countryCode);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JsonArray array = JsonParser.parseString(body).getAsJsonArray();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String BuildURL(String city, String countryCode){
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + ","
                     + "," + countryCode;
        url += "&limit=5&appid=" + apiKey;
        return url;
    }
}
