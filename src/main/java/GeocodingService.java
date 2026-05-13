import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import models.Coordinates;

public class GeocodingService {
    String apiKey = System.getenv("GEO_CODING_KEY");
    HttpClient client = HttpClient.newHttpClient();
    public Coordinates APIGCRequest(String city){
        return APIGCRequest(city, null);
    }
    public Coordinates APIGCRequest(String city, String countryCode) {
        String url = BuildURL(city, countryCode);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JsonArray array = JsonParser.parseString(body).getAsJsonArray();
            double lat = array.get(0).getAsJsonObject().get("lat").getAsDouble();
            double lon = array.get(0).getAsJsonObject().get("lon").getAsDouble();
            return new Coordinates(lat, lon);
        } catch (IOException | InterruptedException e) {
            System.out.println("deu merda irmao");
        }
        return null;
    }
    private String BuildURL(String city, String country){
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city
                     + "," + country;
        url += "&limit=5&appid=" + apiKey;
        System.out.println(url);
        return url;
    }
}
