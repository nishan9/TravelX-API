package com.app.travelx.database.bookings;

import java.util.ArrayList;

public class NewBookingModel {
    private ArrayList<Booking> bookings;
    private String auth0id;

    public NewBookingModel(String auth0id, ArrayList<Booking> flightList){
        this.bookings = flightList;
        this.auth0id = auth0id;
    }

    public NewBookingModel() {

    }

    public ArrayList<Booking> getBookingList() {
        return bookings;
    }

    public void setFlightList(ArrayList<Booking> bookingList) {
        this.bookings = bookingList;
    }

    public String getAuth0id() {
        return auth0id;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setAuth0id(String auth0id) {
        this.auth0id = auth0id;
    }
}
