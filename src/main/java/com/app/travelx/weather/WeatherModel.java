package com.app.travelx.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WeatherModel {
    private int summary;
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

    public WeatherModel(int summary, float maxTemp, float minTemp, float maxApparentTemp, float minApparentTemp, float uvMax, float rainSum, int precipitationProbMax, float snowSum, String sunrise, String sunset) {
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
}
