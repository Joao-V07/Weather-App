import models.GeoResult;
import models.Location;
import models.WeatherData;

public class WeatherController {
    FormattingService formatter = new FormattingService();
    GeocodingService geocoder = new GeocodingService();
    WeatherService weatherApi = new WeatherService();

    public WeatherData search(String input){
            Location location = formatter.formatInput(input);
            GeoResult coords = geocoder.APIGCRequest(location.city, location.country);
            return weatherApi.APIRequest(coords.lat, coords.lon, coords.city, coords.country);
    }
}
