package weather.weather_app.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.weather_app.Model.WeatherResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private final String api_key= "1394e725ca39475eb989f27b6788d7fb";
    private final String api_url= "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}&units=metric";
    public WeatherResponse getWeather(String city)
    {
        RestTemplate restTemplate = new RestTemplate();

        // Create a map for the URI variables
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("city", city);
        uriVariables.put("api_key", api_key);

        // Call the API using the URL and the variables

        return restTemplate.getForObject(api_url, WeatherResponse.class, uriVariables);
    }
}
