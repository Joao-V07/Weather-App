import models.GeoResult;
import models.Location;
import models.WeatherData;

public class WeatherController {
    FormattingService formatter = new FormattingService();
    GeocodingService geocoder = new GeocodingService();
    WeatherService weatherApi = new WeatherService();

    /**
     * Call's the necessary methods to extract weather data from the user's input.
     * @param input User's input
     * @return Necessary data extracted from the geocoding and weather service API.
     */
    public WeatherData search(String input){
            Location location = formatter.formatInput(input);
            GeoResult coords = geocoder.APIGCRequest(location.city, location.country);
            return weatherApi.APIRequest(coords.lat, coords.lon, coords.city, coords.country);
    }
}
