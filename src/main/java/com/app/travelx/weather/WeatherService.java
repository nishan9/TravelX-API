package com.app.travelx.weather;

import com.app.travelx.amadeus.FlightInfoModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<WeatherModel> getWeather(String latitude, String longitude, String date) {

        try {
            if (checkDateFormat(date) && isDateWithin16Days(date)) {
                String url = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+
                        "&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min," +
                        "sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,snowfall_sum,precipitation_hours," +
                        "precipitation_probability_max,windspeed_10m_max&timezone=GMT&start_date="+date+"&end_date="+date+"&forecast_days=16";
                String response = restTemplate.getForObject(url, String.class);
                JSONObject all = new JSONObject(response);

                JSONObject daily = all.getJSONObject("daily");

                String time = daily.getJSONArray("time").getString(0);
                int code = daily.getJSONArray("weathercode").getInt(0);
                String summary = weatherCode(code);
                float maxTemp = daily.getJSONArray("temperature_2m_max").getFloat(0);
                float minTemp = daily.getJSONArray("temperature_2m_min").getFloat(0);
                float maxApparentTemp = daily.getJSONArray("apparent_temperature_max").getFloat(0);
                float minApparentTemp = daily.getJSONArray("apparent_temperature_min").getFloat(0);
                float wind = daily.getJSONArray("windspeed_10m_max").getFloat(0);
                int rainSum = 0;
                int precipitationProbMax = 0;
                float snowSum = (float) 0.0;
                if (!daily.getJSONArray("precipitation_probability_max").isNull(0)) {
                    precipitationProbMax = daily.getJSONArray("precipitation_probability_max").getInt(0);
                }
                if (!daily.getJSONArray("rain_sum").isNull(0)){
                    rainSum = daily.getJSONArray("rain_sum").getInt(0);
                }
                if (!daily.getJSONArray("snowfall_sum").isNull(0)){
                    snowSum = daily.getJSONArray("snowfall_sum").getFloat(0);
                }
                String sunrise = daily.getJSONArray("sunrise").getString(0);
                String sunset = daily.getJSONArray("sunset").getString(0);

                WeatherModel weatherModel = new WeatherModel(time, summary, maxTemp, minTemp, maxApparentTemp,
                        minApparentTemp,
                        wind, rainSum, precipitationProbMax, snowSum, sunrise, sunset);
                return new ResponseEntity<>(weatherModel, HttpStatus.OK);
            } else {
                return null;
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            throw e;
        }
    }

    public ResponseEntity<ArrayList<WeatherModel>> detailedWeather(String latitude, String longitude, String date){
        try{
            if (checkDateFormat(date)){
                LocalDate startDate = calcStart(date);
                ArrayList<WeatherModel> weekWeather = new ArrayList<>();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                for (int i = 0; i < 6; i++) {
                    String formattedDate = startDate.plusDays(i).format(dateFormatter);
                    weekWeather.add((getWeather(latitude, longitude, formattedDate)).getBody());
                }
                return new ResponseEntity<>(weekWeather, HttpStatus.OK);
            }else{
                return null;
            }
        }catch (HttpClientErrorException e){
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            throw e;
        }
    }

    public String weatherCode(int code) {
        if (code <= 1) {
            return "Sunny";
        } else if (code <= 3) {
            return "Cloudy";
        } else if (code <= 49) {
            return "Fog";
        } else if (code <= 57) {
            return "Drizzle";
        } else if (code <= 67 || (code >= 80 && code <= 82)) {
            return "Rain";
        } else if (code <= 77 || code == 85 || code == 86) {
            return "Snow";
        } else {
            return "Thunderstorm";
        }
    }

    public boolean checkDateFormat(String inputDate) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputDate);

        return matcher.matches();
    }

    public boolean isDateWithin16Days(String inputDate) {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(inputDate, formatter);

        long daysDifference = ChronoUnit.DAYS.between(currentDate, parsedDate);

        return Math.abs(daysDifference) <= 16;
    }

    public LocalDate calcStart(String inputDate) {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(inputDate, formatter);

        long daysDifference = ChronoUnit.DAYS.between(currentDate, parsedDate);
        if (Math.abs(daysDifference) <= 10){
            return parsedDate;
        }else if(Math.abs(daysDifference) <= 16) {
            return currentDate.plusDays(10);
        }else{
            return currentDate;
        }
    }


}