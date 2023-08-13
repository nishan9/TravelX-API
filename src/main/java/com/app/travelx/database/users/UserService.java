package com.app.travelx.database.users;
import com.app.travelx.database.bookings.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User userExists(String auth0id){
        Optional<User> user = repo.findById(auth0id);
        if (!user.isPresent()){
            throw new UserNotFound("User Not Found!");
        }
        return user.get();
    }
    public List<Integer> addBooking(List<Booking> bookings, String auth0id, String email, String phone){
        int numOfBookings = bookings.size();
        User owner = userExists(auth0id);
        if (owner.getBookings().isEmpty()){
            owner.setBookings(bookings);
        } else {
            bookings.addAll(owner.getBookings());
            owner.setBookings(bookings);
        }

        repo.save(owner);
        List<Integer> bookingIDs = new ArrayList<>();
        for (int i = 0; i < numOfBookings; i++) {
            bookingIDs.add(bookings.get(i).getBookingid());
        }
        return bookingIDs;
    }

    public void ChangeIsPaid(List<Integer> bookingIDs, String auth0id){
        User owner = userExists(auth0id);
        List<Booking> bookingsList = new ArrayList<>();
        bookingsList = owner.getBookings();
        bookingsList.forEach(booking -> {if (bookingIDs.contains(booking.getBookingid())) {booking.setPaid(true);}});
        owner.setBookings(bookingsList);
        repo.save(owner);
    }

    public List<Booking> getUnPaidBookings(String auth0id){
        User owner = userExists(auth0id);
        return owner.getBookings().stream().filter( booking -> !booking.isPaid()).collect(Collectors.toList());
    }

    public List<Booking> upComingBoookings(String auth0id) throws Exception{
        User owner = userExists(auth0id);

        return owner.getBookings().stream().filter( booking -> {
            try {
                return booking.isPaid() && convertDate(booking.getDepartDateTime()).compareTo(new Date()) > 0;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Date convertDate(String ISOStringDate) throws Exception{
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return df2.parse(ISOStringDate);
        }catch (Exception e){
            throw new Exception("Date Transformation Failed");
        }
    }
}
