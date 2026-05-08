package models;

public class WeatherData {
    public String weatherCondition;
    public String weatherDescription;
    public int temp;
    public int feelsLike;
    public int tempMax;
    public int tempMin;
    public int humidity;
    public double windSpeed;
    public int windDir;

    public WeatherData(String weatherCondition, String weatherDescription, int temp, int feelsLike, int tempMax, int tempMin, int humidity, double windSpeed, int windDir){
        this.weatherCondition = weatherCondition;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
    }
}
