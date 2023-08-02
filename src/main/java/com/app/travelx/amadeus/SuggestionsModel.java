package com.app.travelx.amadeus;

public class SuggestionsModel {
    private String label;
    private String city;
    private String name;
    public SuggestionsModel(String name, String label, String city) {
        this.name = name;
        this.label = label;
        this.city = city;
    }
}
