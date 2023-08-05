package com.app.travelx.database.users;
import com.app.travelx.database.flights.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void addFlights(List<Flight> flights, String auth0id){
        Optional<User> user = repo.findById(auth0id);
        if (!user.isPresent()){
            System.out.println("User not found");
        }
        User owner = user.get();
        if (owner.getFlightList().isEmpty()){
            owner.setFlightList(flights);
        } else {
            flights.addAll(owner.getFlightList());
            owner.setFlightList(flights);
        }
        repo.save(owner);
    }
}
