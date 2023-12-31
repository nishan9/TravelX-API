package com.app.travelx.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * format of returning weather data
 */
@AllArgsConstructor
@Getter
@Setter
public class WeatherModel {
    private String date;
    private String summary;
    private float maxTemp;
    private float minTemp;
    private float maxApparentTemp;
    private float minApparentTemp;
    private float wind;
    private float rainSum;
    private int precipitationProbMax;
    private float uvMax;
    private String sunrise;
    private String sunset;
}
