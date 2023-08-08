package com.app.travelx.weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "f9ec786fc529e0b49f6e586ba83a6f1e";

    public JSONObject getWeather(String cityName, String units) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=" + units + "&appid=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        return new JSONObject(response);
    }

    public JSONArray extractWeatherArray(JSONObject weatherData) {
        return weatherData.getJSONArray("weather");
    }

    public JSONObject extractMain(JSONObject weatherData) {
        return weatherData.getJSONObject("main");
    }

    public JSONObject extractSys(JSONObject weatherData) {
        return weatherData.getJSONObject("sys");
    }

    public JSONObject extractWind(JSONObject weatherData) {
        return weatherData.getJSONObject("wind");
    }
}
