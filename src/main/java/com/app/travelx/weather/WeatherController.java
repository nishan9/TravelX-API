package com.app.travelx.weather;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.app.travelx.amadeus.SuggestionsModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Set;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping()
    public ResponseEntity<WeatherModel> weather(@RequestParam(required = true) String latitude,
    //public JSONArray weather(@RequestParam(required = true) String latitude,
                               @RequestParam(required = true) String longitude,
                               @RequestParam(required = true) String date) throws ResponseException {
        //int year = (int) date.substring(0,4);
        return service.getWeather(latitude, longitude, date);
    }



//    @GetMapping
//    public JSONObject getWeather(@RequestParam String cityName) {
//        return weatherService.getWeather(cityName);
//    }
//
//    @GetMapping("/weatherArray")
//    public JSONArray getWeatherArray(@RequestParam String cityName) {
//        JSONObject weatherData = weatherService.getWeather(cityName);
//        return weatherService.extractWeatherArray(weatherData);
//    }
//
//    @GetMapping("/main")
//    public JSONObject getMain(@RequestParam String cityName) {
//        JSONObject weatherData = weatherService.getWeather(cityName);
//        return weatherService.extractMain(weatherData);
//    }
//
//    @GetMapping("/sys")
//    public JSONObject getSys(@RequestParam String cityName) {
//        JSONObject weatherData = weatherService.getWeather(cityName);
//        return weatherService.extractSys(weatherData);
//    }
//
//    @GetMapping("/wind")
//    public JSONObject getWind(@RequestParam String cityName) {
//        JSONObject weatherData = weatherService.getWeather(cityName);
//        return weatherService.extractWind(weatherData);
//    }
}
