package com.app.travelx.weather;
import com.amadeus.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping()
    public ResponseEntity<WeatherModel> weather(@RequestParam(required = true) String latitude,
                                                @RequestParam(required = true) String longitude,
                                                @RequestParam(required = true) String date) throws ResponseException {
        return service.getWeather(latitude, longitude, date);
    }


}