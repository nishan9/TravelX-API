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


    /**
     * it gives a weather daily summary of the date requested unless it is outside of the next 16 days, then it returns null
     * @param latitude coordinate of airport
     * @param longitude coordinate of airport
     * @param date of travel
     * @return a daily summary of the weather requested with the given coordinates and date
     */
    public ResponseEntity<WeatherModel> getWeather(String latitude, String longitude, String date) {

        try {
            if (checkDateFormat(date) && isDateWithin16Days(date)) {
                String url = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+
                        "&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min," +
                        "sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,snowfall_sum,precipitation_hours," +
                        "precipitation_probability_max,windspeed_10m_max&timezone=GMT&start_date="+date+"&end_date="+date+"&forecast_days=16&timezone=auto";
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
                float uvMax = daily.getJSONArray("uv_index_max").getFloat(0);
                if (!daily.getJSONArray("precipitation_probability_max").isNull(0)) {
                    precipitationProbMax = daily.getJSONArray("precipitation_probability_max").getInt(0);
                }
                if (!daily.getJSONArray("rain_sum").isNull(0)){
                    rainSum = daily.getJSONArray("rain_sum").getInt(0);
                }
//                if (!daily.getJSONArray("snowfall_sum").isNull(0)){
//                    snowSum = daily.getJSONArray("snowfall_sum").getFloat(0);
//                }
                String sunrise = daily.getJSONArray("sunrise").getString(0);
                String sunset = daily.getJSONArray("sunset").getString(0);

                WeatherModel weatherModel = new WeatherModel(time, summary, maxTemp, minTemp, maxApparentTemp,
                        minApparentTemp, wind, rainSum, precipitationProbMax, uvMax, sunrise, sunset);
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

    /**
     * gives daily weather summary for 6 days from requested day unless it is outside of 16 days then it returns next 6 days from current date or next 6 days from 10 days
     * @param latitude coordinate of airport
     * @param longitude coordinate of airport
     * @param date of travel date
     * @return daily summary of 6 days weather
     */
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

    /**
     * converts weather code into more understandable word summary
     * @param code WMO code
     * @return the word describing the daily summary
     */
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

    /**
     * checks the input date is in the right format
     * @param inputDate of travel
     * @return boolean if it is in correct format or not
     */
    public boolean checkDateFormat(String inputDate) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputDate);

        return matcher.matches();
    }

    /**
     * checks if input date is within 16 days
     * @param inputDate of travel
     * @return boolean to test if it is within 16 days
     */
    public boolean isDateWithin16Days(String inputDate) {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(inputDate, formatter);

        long daysDifference = ChronoUnit.DAYS.between(currentDate, parsedDate);

        return Math.abs(daysDifference) <= 16;
    }

    /**
     * calculates what the start date will be to calculate the next 6 days worth of data since api call cannot give weather outside of 16 days
     * @param inputDate of travel
     * @return start date to calculate the next 6 days worth of data that is closest to travel date or sends current date if not available
     */
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