package weather.weather_app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import weather.weather_app.Model.WeatherResponse;
import weather.weather_app.Service.WeatherService;

import java.time.Year;

@Controller
public class PageController {

    private final WeatherService weatherService;

    public PageController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/weather-dashboard")
    public String getWeather(Model model)
    {
        return "dashboard";
    }

    @PostMapping("/getWeather")
    public String searchCity(@RequestParam("city") String city, Model model) {
        try {
            // Call service to fetch weather data
            WeatherResponse weatherResponse = weatherService.getWeather(city);

            // Check if the response has main data
            if (weatherResponse.getMain() != null) {
                model.addAttribute("temperature", weatherResponse.getMain().getTemp());
                model.addAttribute("humidity", weatherResponse.getMain().getHumidity());
            }

            // Check if the response has wind data
            if (weatherResponse.getWind() != null) {
                model.addAttribute("windSpeed", weatherResponse.getWind().getSpeed());
            }

            model.addAttribute("city", city);
            model.addAttribute("error", null);  // Clear any previous error message

        } catch (HttpClientErrorException.NotFound e) {
            // City not found
            model.addAttribute("error", "City not found. Please try again.");
            model.addAttribute("city", city);
            model.addAttribute("temperature", null);
            model.addAttribute("humidity", null);
            model.addAttribute("windSpeed", null);
        } catch (Exception e) {
            // Handle other exceptions
            model.addAttribute("error", "An unexpected error occurred. Please try again.");
            model.addAttribute("city", city);
            model.addAttribute("temperature", null);
            model.addAttribute("humidity", null);
            model.addAttribute("windSpeed", null);
        }

        return "dashboard";
    }
}
