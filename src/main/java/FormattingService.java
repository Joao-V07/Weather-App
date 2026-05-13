import models.Location;
import java.util.Locale;

public class FormattingService {

    public Location formatInput(String text){
        try {
            String[] input = text.trim().split(",");
            String city = input[0].replace(" ", "%20");
            String countryName = input.length > 1 ? countryNameToCode(input[1].trim()) : null;
            return new Location(city, countryName);
        } catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e){
            System.out.println("ta errado");
            return null;
        }
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
