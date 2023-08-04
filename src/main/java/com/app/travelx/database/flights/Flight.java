package com.app.travelx.database.flights;
import com.app.travelx.database.flights.Flight;
import com.app.travelx.database.passengers.Passenger;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "_flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightid;
    private String origin;
    private String destination;

    @Column(name = "depart_time")
    private String departTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Passenger> passengerid;
    private boolean nonstop;

    private String airline;

    private int price;

    private String currency;

    public Flight() {
        super();
    }
    public Flight(String origin, String departTime, String arrivalTime, boolean nonstop, String airline, int price, String currency) {
        this.origin = origin;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.nonstop = nonstop;
        this.airline = airline;
        this.price = price;
        this.currency = currency;
    }

    public List<Passenger> getPassengerList() {
        return passengerid;
    }

    public void setPassengerList(List<Passenger> passengerid) {
        this.passengerid = passengerid;
    }

    public int getFlightid() {
        return flightid;
    }

    public void setFlightid(int flightid) {
        this.flightid = flightid;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public boolean isNonstop() {
        return nonstop;
    }

    public void setNonstop(boolean nonstop) {
        this.nonstop = nonstop;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}