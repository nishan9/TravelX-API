package com.app.travelx.amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value="api", produces = MediaType.APPLICATION_JSON_VALUE)

public class AmadeusController {



	//getlocations
	@GetMapping("/locations")
	public ArrayList<SuggestionsModel> locations(@RequestParam(required=true) String keyword) throws ResponseException {
		Location [] results = AmadeusConnect.INSTANCE.location(keyword);
		ArrayList<SuggestionsModel> suggestions = new ArrayList<>();
		for (Location location : results) {
			String iataCode = location.getIataCode();
			String cityName = location.getAddress().getCityName();
			String airportName = location.getName();
			suggestions.add(new SuggestionsModel(airportName, iataCode, cityName));
		}
		return suggestions;
	}

	@GetMapping("/onewayflightsnonstop")
	//public FlightOfferSearch[] onewayflightsnonstop (@RequestParam(required=true) String origin,
	public ResponseEntity<ArrayList<FlightInfoModel>> onewayflightsnonstop (@RequestParam(required=true) String origin,
													   @RequestParam(required=true) String destination,
													   @RequestParam(required=true) String departDate,
													   @RequestParam(required=true) String adults)
													 throws ResponseException {
		FlightOfferSearch [] search = AmadeusConnect.INSTANCE.onewayflightsnonstop(origin, destination, departDate, adults);
		ArrayList<FlightInfoModel> flights = new ArrayList<>();
		for (int i=0; i<search.length; i++){
			String departLocation = search[i].getItineraries()[0].getSegments()[0].getDeparture().getIataCode();
			String arrivalLocation = search[i].getItineraries()[0].getSegments()[0].getArrival().getIataCode();
			String departTime = search[i].getItineraries()[0].getSegments()[0].getDeparture().getAt();
			String arrivalTime = search[i].getItineraries()[0].getSegments()[0].getArrival().getAt();
			String airline = search[i].getItineraries()[0].getSegments()[0].getCarrierCode();
			String currerncy = search[i].getPrice().getCurrency();
			double totalPrice = search[i].getPrice().getGrandTotal();
			int availableSeats = search[i].getNumberOfBookableSeats();
			String duration = search[i].getItineraries()[0].getDuration().substring(2);
			flights.add(new FlightInfoModel(departLocation, arrivalLocation, departTime, arrivalTime, airline, currerncy, totalPrice,
					availableSeats, duration));
		}
		//return flights;
		return new ResponseEntity<> (flights, HttpStatus.CREATED);
	}


}