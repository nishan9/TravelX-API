package com.app.travelx.weather;

import com.amadeus.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    /**
     *
     * @param latitude coordinate of airport
     * @param longitude coordinate of airport
     * @param date of travel in format yyyy-mm-dd
     * @return weather details for requested date and location such as temperature, wind etc.
     * @throws ResponseException
     */
    @GetMapping()
    public ResponseEntity<WeatherModel> weather(@RequestParam(required = true) String latitude,
            @RequestParam(required = true) String longitude,
            @RequestParam(required = true) String date) throws ResponseException {
        return service.getWeather(latitude, longitude, date);
    }


    /**
     *
     * @param latitude coordinate of airport
     * @param longitude coordinate of airport
     * @param date of travel
     * @return array of daily summary of weather for the next 6 days or 6 days from current day
     * @throws ResponseException
     */
    @GetMapping("/week")
    public ResponseEntity<ArrayList<WeatherModel>> detailWeather(@RequestParam(required = true) String latitude,
                                                                 @RequestParam(required = true) String longitude,
                                                                 @RequestParam(required = true) String date) throws ResponseException {

        return service.detailedWeather(latitude, longitude, date);
    }

}
