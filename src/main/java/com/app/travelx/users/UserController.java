package com.app.travelx.users;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

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

    @PostMapping("details")
    public ResponseEntity getUserByID(@RequestBody Auth0IDModel request) {
        Optional<User> userdata = userRepository.findById(request.getAuth0id());
          if (userdata.isPresent()) {
              return new ResponseEntity<>(userdata.get(), HttpStatus.OK);
          } else {
              throw new UserNotFound("Invalid User Id");
          }
    }
}