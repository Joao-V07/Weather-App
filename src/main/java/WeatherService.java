
public class WeatherService {
    public void APIRequest(){
    GeocodingService GC_service = new GeocodingService();
    double[] coords = GC_service.APIGCRequest("Sydney");
    double lat = coords[0];
    double lon = coords[1];
        System.out.print("Lat: " + lat + " Lon: " + lon);
    }
}
