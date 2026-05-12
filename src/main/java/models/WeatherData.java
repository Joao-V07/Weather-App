package models;

public class WeatherData {
    public String weatherCondition;
    public String weatherDescription;
    public int temp;
    public int feelsLike;
    public int humidity;
    public double windSpeed;
    public int windDir;
    public int cloudiness;
    public String city;
    public String country;

    public WeatherData(String weatherCondition, String weatherDescription, int temp, int feelsLike, int humidity, double windSpeed, int windDir, int cloudiness, String city, String country){
        this.weatherCondition = weatherCondition;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.cloudiness = cloudiness;
        this.city = city;
        this.country = country;
    }
}
