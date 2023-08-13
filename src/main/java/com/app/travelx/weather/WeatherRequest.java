package com.app.travelx.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherRequest {
    private String cityName;
    private String units;
}
