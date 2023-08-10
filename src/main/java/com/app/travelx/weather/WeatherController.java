package com.app.travelx.weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public JSONObject getWeather(@RequestParam String cityName, @RequestParam String units) {
        return weatherService.getWeather(cityName, units);
    }

    @GetMapping("/weatherArray")
    public JSONArray getWeatherArray(@RequestParam String cityName, @RequestParam String units) {
        JSONObject weatherData = weatherService.getWeather(cityName, units);
        return weatherService.extractWeatherArray(weatherData);
    }

    @GetMapping("/main")
    public JSONObject getMain(@RequestParam String cityName, @RequestParam String units) {
        JSONObject weatherData = weatherService.getWeather(cityName, units);
        return weatherService.extractMain(weatherData);
    }

    @GetMapping("/sys")
    public JSONObject getSys(@RequestParam String cityName, @RequestParam String units) {
        JSONObject weatherData = weatherService.getWeather(cityName, units);
        return weatherService.extractSys(weatherData);
    }

    @GetMapping("/wind")
    public JSONObject getWind(@RequestParam String cityName, @RequestParam String units) {
        JSONObject weatherData = weatherService.getWeather(cityName, units);
        return weatherService.extractWind(weatherData);
    }
}
