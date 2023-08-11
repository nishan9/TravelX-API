package com.app.travelx.database.users;
import com.app.travelx.database.bookings.Booking;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "_user")
public class User {

    @Id
    private String auth0id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Booking> bookings;
    public User() {
        super();
    }

    public List<Booking> getBookingList() {
        return bookings;
    }

    public void setBookingList(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public User(String auth0id, String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.auth0id = auth0id;
    }

    public String getAuth0id() {
        return auth0id;
    }

    public void setAuth0id(String auth0id) {
        this.auth0id = auth0id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}