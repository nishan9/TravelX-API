package com.app.travelx.amadeus;

public class SuggestionsModel {
    private String label;
    private String city;
    private String name;
    private double latitude;
    private double longitude;
    public SuggestionsModel(String name, String label, String city, double latitude, double longitude) {
        this.name = name;
        this.label = label;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
