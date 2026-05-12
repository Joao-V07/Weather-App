import models.Coordinates;
import models.Location;
import models.WeatherData;

public class WeatherController {
    FormattingService formatter = new FormattingService();
    GeocodingService geocoder = new GeocodingService();
    WeatherService weatherApi = new WeatherService();

    public WeatherData search(String input){
            if (input.equals("Enter \"City\" or \"City, Country\" (e.g. Sydney or Rio de Janeiro, BR)")){

            }
            Location location = formatter.formatInput(input);
            Coordinates coords = geocoder.APIGCRequest(location.city, location.country);
            return weatherApi.APIRequest(coords.lat, coords.lon);
    }
}
