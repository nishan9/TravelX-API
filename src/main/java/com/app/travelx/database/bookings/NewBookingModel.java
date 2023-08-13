package com.app.travelx.database.bookings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewBookingModel {

    private ArrayList<Booking> bookings;

    private String auth0id;

    private String phone;

    private String email;

}
