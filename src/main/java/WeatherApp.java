
public class WeatherApp{
    static void main(String[] args){
        //new UI();
        GeocodingService GC_service = new GeocodingService();
        WeatherService weather_service = new WeatherService();
        weather_service.APIRequest();
    }
}
