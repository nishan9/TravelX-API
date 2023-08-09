package com.app.travelx.weather;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.util.ArrayList;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();
    //public JSONArray getWeather(String latitude, String longitude, String date){
    public ResponseEntity<WeatherModel> getWeather(String latitude, String longitude, String date){
        if (checkDateFormat(date) && isDateWithin16Days(date)){
             String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
                    "&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min," +
                    "sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,snowfall_sum,precipitation_hours," +
                    "precipitation_probability_max,windspeed_10m_max&timezone=GMT&start_date=" + date + "&end_date=" + date;
            String response = restTemplate.getForObject(url, String.class);
            JSONObject all = new JSONObject(response);

            JSONObject daily = all.getJSONObject("daily");

            int summary = daily.getJSONArray("weathercode").getInt(0);
            float maxTemp = daily.getJSONArray("temperature_2m_max").getFloat(0);
            float minTemp = daily.getJSONArray("temperature_2m_min").getFloat(0);
            float maxApparentTemp = daily.getJSONArray("apparent_temperature_max").getFloat(0);
            float minApparentTemp = daily.getJSONArray("apparent_temperature_min").getFloat(0);
            float uvMax = daily.getJSONArray("uv_index_max").getFloat(0);
            int rainSum = daily.getJSONArray("rain_sum").getInt(0);
            int precipitationProbMax = daily.getJSONArray("precipitation_probability_max").getInt(0);
            float snowSum = daily.getJSONArray("snowfall_sum").getFloat(0);
            String sunrise = daily.getJSONArray("sunrise").getString(0);
            String sunset = daily.getJSONArray("sunset").getString(0);

            WeatherModel weatherModel = new WeatherModel(summary, maxTemp, minTemp, maxApparentTemp, minApparentTemp,
                    uvMax, rainSum, precipitationProbMax, snowSum, sunrise, sunset);
            return new ResponseEntity<> (weatherModel, HttpStatus.OK);
        }else{
            return null;
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

}
