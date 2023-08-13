package com.app.travelx.WeatherTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.travelx.weather.WeatherModel;
import com.app.travelx.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    public void testGetWeatherSuccess() {

        String latitude = "50";
        String longitude = "0";
        String date = "2023-08-14";

        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
                "&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,"
                +
                "sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,snowfall_sum,precipitation_hours," +
                "precipitation_probability_max,windspeed_10m_max&timezone=GMT&start_date=" + date + "&end_date=" + date;

        String sampleResponse = "{ \"daily\": {\"weathercode\":[2],\"temperature_2m_max\":[25.5],\"temperature_2m_min\":[10],\"apparent_temperature_max\":[28],\"apparent_temperature_min\":[12],\"uv_index_max\":[5],\"rain_sum\":[10],\"precipitation_probability_max\":[80],\"snowfall_sum\":[0],\"sunrise\":[\"06:00\"],\"sunset\":[\"19:00\"]}}";

        when(restTemplate.getForObject(url, String.class)).thenReturn(sampleResponse);

        ResponseEntity<WeatherModel> response = weatherService.getWeather("50", "0", "2023-08-14");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Partly Cloudy", response.getBody().getSummary());
    }

    @Test
    public void testGetWeatherEmptyAPIResponse() {
        String latitude = "";
        String longitude = "";
        String date = "";

        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
                "&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,"
                +
                "sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,snowfall_sum,precipitation_hours," +
                "precipitation_probability_max,windspeed_10m_max&timezone=GMT&start_date=" + date + "&end_date=" + date;

        when(restTemplate.getForObject(url, String.class)).thenReturn("");

        ResponseEntity<WeatherModel> response = weatherService.getWeather(latitude, longitude, date);
        assertNull(response);
    }

    @Test
    public void testGetWeatherInvalidLatitudeLongitude() {
        String latitude = "200";
        String longitude = "0";
        String date = "2023-08-14";

        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
                "&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,"
                +
                "sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,snowfall_sum,precipitation_hours," +
                "precipitation_probability_max,windspeed_10m_max&timezone=GMT&start_date=" + date + "&end_date=" + date;

        when(restTemplate.getForObject(url, String.class))
                .thenThrow(new org.springframework.web.client.HttpClientErrorException(HttpStatus.BAD_REQUEST));

        ResponseEntity<WeatherModel> response = weatherService.getWeather(latitude, longitude, date);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetWeatherInvalidDate() {
        ResponseEntity<WeatherModel> response = weatherService.getWeather("50", "0", "2023-02-30");
        assertNull(response);
    }

    @Test
    public void testGetWeatherFarFutureDate() {
        ResponseEntity<WeatherModel> response = weatherService.getWeather("50", "0", "3030-08-14");
        assertNull(response);
    }

    @Test
    public void testWeatherCodeBoundaryValues() {
        assertEquals("Rain", weatherService.weatherCode(80));
        assertEquals("Snow", weatherService.weatherCode(85));
    }

    @Test
    public void testWeatherCode() {
        assertEquals("Clear Sky", weatherService.weatherCode(1));
        assertEquals("Partly Cloudy", weatherService.weatherCode(3));
        assertEquals("Fog", weatherService.weatherCode(49));
        assertEquals("Drizzle", weatherService.weatherCode(57));
        assertEquals("Rain", weatherService.weatherCode(67));
        assertEquals("Snow", weatherService.weatherCode(77));
        assertEquals("Thunderstorm", weatherService.weatherCode(90));
    }

    @Test
    public void testCheckDateFormat() {
        assertTrue(weatherService.checkDateFormat("2023-08-20"));
        assertFalse(weatherService.checkDateFormat("2023-08-200"));
    }

    @Test
    public void testIsDateWithin16Days() {
        assertTrue(weatherService.isDateWithin16Days("2023-08-14"));
        assertFalse(weatherService.isDateWithin16Days("2023-09-01"));
    }
}
