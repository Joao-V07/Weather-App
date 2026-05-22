import models.Location;
import java.util.Locale;

public class FormattingService {

    public Location formatInput(String text){
        if (text.isEmpty()) throw new ExceptionHandling("You must enter at least city name");
        String[] input = text.trim().split(",");
        if (input.length == 0) throw new ExceptionHandling("You must enter at least city name");
        String city = input[0].replace(" ", "%20");
        String countryName = input.length > 1 ? countryNameToCode(input[1].trim()) : null;
        if (city.isEmpty()) throw new ExceptionHandling("You must enter at least city name");
        else  return new Location(city, countryName);
    }

    public String countryNameToCode(String countryName){
        if (countryName == null || countryName.isBlank()){
            return null;
        }
        for (String countryCode : Locale.getISOCountries()){
            Locale locale = new Locale("", countryCode);
            if (locale.getDisplayCountry(Locale.ENGLISH).equalsIgnoreCase(countryName)){
                return countryCode;
            }
        }
        return countryName.toUpperCase();
    }
}
