
public class Main{
    public static void main(String[] args){
        //new UI();
        GeocodingService GCService = new GeocodingService();
        WeatherService weatherService = new WeatherService();
        //weatherService.APIRequest();
        FormattingService formattingService = new FormattingService();
        formattingService.formatInput();
    }
}
