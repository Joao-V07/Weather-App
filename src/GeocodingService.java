import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeocodingService {
    String apiKey = System.getenv("GEO_CODING_KEY");
    HttpClient client = HttpClient.newHttpClient();

    public void APIRequest(String city, String stateCode, String countryCode) {
        String url = BuildURL(city, stateCode, countryCode);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String BuildURL(String city, String stateCode, String countryCode){
    String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city;
    if (stateCode != null && !stateCode.isEmpty()){
        url += "," + stateCode;
        }
    if (countryCode != null && !countryCode.isEmpty()){
        url += "," + countryCode;
    }
    url += "&limit={limit}&appid=" + apiKey;
    return url;
    }
}
