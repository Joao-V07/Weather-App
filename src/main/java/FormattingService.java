import models.Location;
import java.util.Locale;

public class FormattingService {
    /**
     * Formats the user's input to correctly send it to the Geocoding API.
     * @param text user's input.
     * @return city and, if provided, country's name.
     */
    public Location formatInput(String text){
        // Checks if user didn't write anything and clicked the search button.
        if (text.isEmpty()) throw new ExceptionHandling("You must enter at least city name");
        String[] input = text.trim().split(",");
        // Checks if user wrote for example only ",", which would not be empty but wouldn't be an acceptable input either.
        if (input.length == 0) throw new ExceptionHandling("You must enter at least city name");
        String city = input[0].replace(" ", "%20");
        // Checks if user wrote a country name e.g. Sydney, Australia.
        String countryName = input.length > 1 ? countryNameToCode(input[1].trim()) : null;
        // Checks if user wrote a city name.
        if (city.isEmpty()) throw new ExceptionHandling("You must enter at least city name");
        else  return new Location(city, countryName);
    }

    /**
     * converts the country name the user wrote to its ISO 3166 code (required for Geocoding API).
     * @param countryName name given by the user (e.g. Australia)
     * @return country code as String (e.g. AU, BR, NZ)
     */
    public String countryNameToCode(String countryName){
        if (countryName == null || countryName.isBlank()){
            return null;
        }
        // Runs an array of all country codes using the Locale library and the "countryCode" variable assigns the current country code.
        for (String countryCode : Locale.getISOCountries()){
            // Creates a variable that assigns all the information about a country based on the country code provided.
            Locale locale = new Locale("", countryCode);
            // Checks if the country's full name located inside the locale variable is the same as the user's input.
            if (locale.getDisplayCountry(Locale.ENGLISH).equalsIgnoreCase(countryName)){
                return countryCode;
            }
        }
        // If the country code wasn't found just return the user's input.
        return countryName.toUpperCase();
    }
}
