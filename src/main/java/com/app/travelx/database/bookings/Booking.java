package com.app.travelx.database.bookings;
import com.app.travelx.database.passengers.Passenger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "_booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingid;
    private String origin;
    private String destination;

    private boolean isPaid;

    @Column(name = "depart_date_time")
    private String departDateTime;

    @Column(name = "arrival_date_time")
    private String arrivalDateTime;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Passenger> passengerid;
    private boolean nonstop;

    private String airline;

    private int price;

    private String currency;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Booking() {
        super();
    }
    public Booking(String origin, String destination, String departDateTime, String arrivalDateTime, boolean nonstop, String airline, int price, String currency) {
        this.isPaid = false;
        this.origin = origin;
        this.destination = destination;
        this.departDateTime = departDateTime;
        this.arrivalDateTime = arrivalDateTime;
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

    public String getDepartDateTime() {
        return departDateTime;
    }

    public void setDepartDateTime(String departDateTime) {
        this.departDateTime = departDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
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