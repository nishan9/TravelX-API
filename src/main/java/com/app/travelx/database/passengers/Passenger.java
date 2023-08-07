package com.app.travelx.database.passengers;
import com.app.travelx.database.flights.Flight;

import javax.persistence.*;

@Entity
@Table(name = "_passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerid;
    @Column(name = "suffix")
    private String suffix;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "passport_number")
    private String passportNumber;

    public Passenger() {
        super();
    }

    public Passenger(String suffix, String firstName, String lastName, String passportNumber) {
        this.suffix = suffix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }
}