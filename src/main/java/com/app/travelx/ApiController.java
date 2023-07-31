package com.app.travelx;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value="/api")

public class ApiController {

	@GetMapping("/locations")
	public ArrayList<Suggestions> locations(@RequestParam(required=true) String keyword) throws ResponseException {
		Location [] results = AmadeusConnect.INSTANCE.location(keyword);
		ArrayList<Suggestions> sugg = new ArrayList<>();
		for (int i = 0; i < results.length - 1; i++) {
			String labelName = results[i].getIataCode();
			String cityName = results[i].getAddress().getCityName();
			String airportName = results[i].getName();
			Suggestions single = new Suggestions(airportName, labelName,cityName);
			sugg.add(single);
		}
		return sugg;
	}

	@GetMapping("/flights")
	public FlightOfferSearch[] flights(@RequestParam(required=true) String origin, 
						  @RequestParam(required=true) String destination,
						  @RequestParam(required=true) String departDate,
						  @RequestParam(required=true) String adults,
						  @RequestParam(required = false) String returnDate) 
						  throws ResponseException {
		return AmadeusConnect.INSTANCE.flights(origin, destination, departDate, adults, returnDate);
	}

	@GetMapping("/onewayflightsnonstop")
	public FlightOfferSearch[] onewayflightsnonstop(@RequestParam(required=true) String origin,
											 @RequestParam(required=true) String destination,
											 @RequestParam(required=true) String departDate,
											 @RequestParam(required=true) String adults)
			throws ResponseException {
		return AmadeusConnect.INSTANCE.onewayflightsnonstop(origin, destination, departDate, adults);
	}
}