package com.app.travelx.database.users;
import java.util.List;
import java.util.Optional;
import com.app.travelx.database.bookings.Booking;
import com.app.travelx.database.bookings.NewBookingModel;
import com.app.travelx.security.BearerTokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    SMSService smsService;

    @Autowired
    private BearerTokenWrapper tokenWrapper;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Optional<User> userdata = userRepository.findById(user.getAuth0id());
        if (userdata.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } else {
            User newuser = new User(user.getAuth0id(), user.getFirstName(), user.getLastName());
            userRepository.save(newuser);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @PostMapping("/details")
    public ResponseEntity getUserByID(@RequestBody Auth0IDModel request) {
        //String sub = jwtDecoder.decodeJWTSub(token);
        //System.out.println(sub);
        Optional<User> userdata = userRepository.findById(request.getAuth0id());
          if (userdata.isPresent()) {
              return new ResponseEntity<>(userdata.get(), HttpStatus.OK);
          } else {
              throw new UserNotFound("Invalid User Id");
          }
    }

    //New bookings, isPaid will be set to false by default
    @PostMapping("/newbooking")
    public ResponseEntity<List<Integer>> newBooking(@RequestBody NewBookingModel request) {
        List<Integer> BookingIDs = userService.addBooking(request.getBookings(), tokenWrapper.getSub(), request.getEmail(), request.getPhone());
        return new ResponseEntity<>(BookingIDs, HttpStatus.OK);
    }


    @PutMapping("/changeIsPaid")
    public ResponseEntity changeIsPaid(@RequestBody ChangeIsPaidModel RequestChange) {
        userService.ChangeIsPaid(RequestChange.BookingIDs, RequestChange.auth0id);
        SMSRequest smsRequest = new SMSRequest("+447596474645", "Thank you for using GetYourWay! You have successfully booked a flight!");
        smsService.sendSMS(smsRequest);
        return new ResponseEntity<>("now paid", HttpStatus.OK);
    }

    @GetMapping("/unpaidBookings")
    public ResponseEntity<List<Booking>> getUnpaidBookings(){
        return new ResponseEntity<>(userService.getUnPaidBookings(tokenWrapper.getSub()), HttpStatus.OK);
    }

    @GetMapping("/upcomingBooking")
    public ResponseEntity getUpcomingBookings(){
        return new ResponseEntity<>(userService.upComingBoookings(tokenWrapper.getSub()), HttpStatus.OK);
    }
  
    @GetMapping("/pastBookings")
    public ResponseEntity getPastBookings(){
        return new ResponseEntity<>(userService.pastBookings(tokenWrapper.getSub()), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user){
        return new ResponseEntity<>(userService.updateDetails(tokenWrapper.getSub(), user.getFirstName(), user.getLastName()), HttpStatus.OK);
    }

}