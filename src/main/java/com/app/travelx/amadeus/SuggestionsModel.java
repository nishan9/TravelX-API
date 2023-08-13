package com.app.travelx.amadeus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SuggestionsModel {
    private String label;
    private String city;
    private String name;
    private double latitude;
    private double longitude;
}
