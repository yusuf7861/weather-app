package weather.weather_app.Model;

import lombok.Data;

@Data
public class WeatherResponse {
    private Main main;
    private Wind wind;
    private String name;

    // Nested class for Main
    @Data
    public static class Main {
        private double temp;
        private int humidity;
    }

    // Nested class for Wind
    @Data
    public static class Wind {
        private double speed;
    }
}
