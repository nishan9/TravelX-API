package com.app.travelx.weather.dto;

public class WeatherRequest {
    private String cityName;
    private String units;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
