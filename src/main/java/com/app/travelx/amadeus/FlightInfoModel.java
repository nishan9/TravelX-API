package com.app.travelx.amadeus;

public class FlightInfoModel {
    private String departLocation;
    private String arrivalLocation;
    private String departTime;
    private String arrivalTime;
    private String airline;
    private String currerncy;
    private double totalPrice;
    private int availableSeats;
    private String duration;

    public FlightInfoModel(String departLocation, String arrivalLocation, String departTime, String arrivalTime, String airline, String currerncy, double totalPrice, int availableSeats, String duration) {
        this.departLocation = departLocation;
        this.arrivalLocation = arrivalLocation;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.currerncy = currerncy;
        this.totalPrice = totalPrice;
        this.availableSeats = availableSeats;
        this.duration = duration;
    }

    public String getDepartLocation() {
        return departLocation;
    }

    public void setDepartLocation(String departLocation) {
        this.departLocation = departLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getCurrerncy() {
        return currerncy;
    }

    public void setCurrerncy(String currerncy) {
        this.currerncy = currerncy;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
