package com.app.travelx.database.users;
import com.app.travelx.database.bookings.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private String phone;

    private String email;

    public User(String auth0id, String firstName, String lastName){
        this.auth0id = auth0id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}