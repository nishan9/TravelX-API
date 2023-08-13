package com.app.travelx.weather;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
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

}
