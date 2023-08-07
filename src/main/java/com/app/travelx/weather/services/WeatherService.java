package com.app.travelx.weather.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class WeatherService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey = "f9ec786fc529e0b49f6e586ba83a6f1e";

    public JSONObject getCurrentWeather(WeatherRequest request) {
        return fetchWeatherData("https://api.openweathermap.org/data/2.5/weather?q=" + request.getCityName() + "&units=" + request.getUnits() + "&appid=" + apiKey);
    }

    public JSONArray getThreeDayForecast(WeatherRequest request) {
        return fetchWeatherData("https://api.openweathermap.org/data/2.5/forecast?q=" + request.getCityName() + "&units=" + request.getUnits() + "&appid=" + apiKey).getJSONArray("list").getJSONArray(0, 3); // Assuming the API returns an ordered list and we just need the first three
    }

    private JSONObject fetchWeatherData(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray returnWeatherArray(WeatherRequest request) {
        return getCurrentWeather(request).getJSONArray("weather");
    }

    public JSONObject returnMain(WeatherRequest request) {
        return getCurrentWeather(request).getJSONObject("main");
    }

    public JSONObject returnSys(WeatherRequest request) {
        return getCurrentWeather(request).getJSONObject("sys");
    }

    public JSONObject returnWind(WeatherRequest request) {
        return getCurrentWeather(request).getJSONObject("wind");
    }
}
