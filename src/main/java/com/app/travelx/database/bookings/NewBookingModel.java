package com.app.travelx.database.bookings;

import com.app.travelx.database.flights.Flight;

import java.util.ArrayList;

public class NewBookingModel {
    private ArrayList<Flight> flights;
    private String auth0id;

    public NewBookingModel(String auth0id, ArrayList<Flight> flightList){
        this.flights = flightList;
        this.auth0id = auth0id;
    }

    public ArrayList<Flight> getFlightList() {
        return flights;
    }

    public void setFlightList(ArrayList<Flight> flightList) {
        this.flights = flightList;
    }

    public String getAuth0id() {
        return auth0id;
    }

    public void setAuth0id(String auth0id) {
        this.auth0id = auth0id;
    }
}
