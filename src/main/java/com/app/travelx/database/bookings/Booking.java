package com.app.travelx.database.bookings;
import com.app.travelx.database.passengers.Passenger;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "_booking")
@Data
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingid;
    private String origin;
    private String destination;

    private boolean isPaid;

    private String phone;

    private String email;

    @Column(name = "depart_date_time")
    private String departDateTime;

    @Column(name = "arrival_date_time")
    private String arrivalDateTime;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Passenger> passengerid;
    private boolean nonstop;

    private String airline;

    private double price;

    private String currency;

}