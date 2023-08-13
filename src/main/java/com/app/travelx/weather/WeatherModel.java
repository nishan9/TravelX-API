package com.app.travelx.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherModel {
    private String summary;
    private float maxTemp;
    private float minTemp;
    private float maxApparentTemp;
    private float minApparentTemp;
    private float uvMax;
    private float rainSum;
    private int precipitationProbMax;
    private float snowSum;
    private String sunrise;
    private String sunset;

    public WeatherModel(String summary, float maxTemp, float minTemp, float maxApparentTemp, float minApparentTemp,
            float uvMax, float rainSum, int precipitationProbMax, float snowSum, String sunrise, String sunset) {
        this.summary = summary;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.maxApparentTemp = maxApparentTemp;
        this.minApparentTemp = minApparentTemp;
        this.uvMax = uvMax;
        this.rainSum = rainSum;
        this.precipitationProbMax = precipitationProbMax;
        this.snowSum = snowSum;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxApparentTemp() {
        return maxApparentTemp;
    }

    public void setMaxApparentTemp(float maxApparentTemp) {
        this.maxApparentTemp = maxApparentTemp;
    }

    public float getMinApparentTemp() {
        return minApparentTemp;
    }

    public void setMinApparentTemp(float minApparentTemp) {
        this.minApparentTemp = minApparentTemp;
    }

    public float getUvMax() {
        return uvMax;
    }

    public void setUvMax(float uvMax) {
        this.uvMax = uvMax;
    }

    public float getRainSum() {
        return rainSum;
    }

    public void setRainSum(float rainSum) {
        this.rainSum = rainSum;
    }

    public int getPrecipitationProbMax() {
        return precipitationProbMax;
    }

    public void setPrecipitationProbMax(int precipitationProbMax) {
        this.precipitationProbMax = precipitationProbMax;
    }

    public float getSnowSum() {
        return snowSum;
    }

    public void setSnowSum(float snowSum) {
        this.snowSum = snowSum;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
