package com.app.travelx.amadeus;

public class SuggestionsModel {
    private String label;
    private String city;
    private String name;
    private double latitude;
    private double longitude;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public SuggestionsModel(String name, String label, String city, double latitude, double longitude) {
        this.name = name;
        this.label = label;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
