package com.app.travelx.amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Array;
import java.util.ArrayList;


@RestController
@CrossOrigin
@RequestMapping("/api/amadeus")
public class AmadeusController {

	@Autowired
	private AmadeusService amadeusService;

	/**
	 * gets available flights and formats it to easily manipulate from api call
	 * @param origin city of depart airport
	 * @param destination city or arrival airport
	 * @param departDate of travel
	 * @param adults number of passengers
	 * @return arraylist of available flights in formated fields from api call
	 * @throws ResponseException
	 */
	@GetMapping
	public ResponseEntity<ArrayList<FlightInfoModel>> getFlights (@RequestParam(required=true) String origin,
																  @RequestParam(required=true) String destination,
																  @RequestParam(required=true) String departDate,
																  @RequestParam(required=true) String adults)
															      throws ResponseException {
		FlightOfferSearch [] search = AmadeusConnect.INSTANCE.flights(origin, destination, departDate, adults);
		ArrayList<FlightInfoModel> flights = new ArrayList<>();
		for (int i=0; i<search.length; i++){
			String departLocation = search[i].getItineraries()[0].getSegments()[0].getDeparture().getIataCode();
			String arrivalLocation = search[i].getItineraries()[0].getSegments()[0].getArrival().getIataCode();
			String departTime = search[i].getItineraries()[0].getSegments()[0].getDeparture().getAt();
			String arrivalTime = search[i].getItineraries()[0].getSegments()[0].getArrival().getAt();
			String airline = search[i].getItineraries()[0].getSegments()[0].getCarrierCode()+ search[i].getItineraries()[0].getSegments()[0].getNumber();
			String currency = search[i].getPrice().getCurrency();
			double totalPrice = search[i].getPrice().getGrandTotal();
			int availableSeats = search[i].getNumberOfBookableSeats();
			String duration = search[i].getItineraries()[0].getDuration().substring(2);
			flights.add(new FlightInfoModel(departLocation, arrivalLocation, departTime, arrivalTime, airline, currency, totalPrice,
					availableSeats, duration));
		}
		return new ResponseEntity<> (flights, HttpStatus.OK);
	}

	/**
	 * gets all airports in the requested city
	 * @param keyword passes the name of the city the airport is in or the airport code
	 * @return the airport code, city, latitude and longitude coordinates
	 * @throws ResponseException
	 */
	@GetMapping("/locations")
	public ResponseEntity<ArrayList<SuggestionsModel>> locations(@RequestParam(required=true) String keyword) throws ResponseException {
		Location [] results = AmadeusConnect.INSTANCE.location(keyword);
		ArrayList<SuggestionsModel> suggestions = new ArrayList<>();
		for (Location location : results) {
			String iataCode = location.getIataCode();
			String cityName = location.getAddress().getCityName();
			String airportName = location.getName();
			double latitude = location.getGeoCode().getLatitude();
			double longitude = location.getGeoCode().getLongitude();
			suggestions.add(new SuggestionsModel(airportName, iataCode, cityName, latitude, longitude));
		}
		return new ResponseEntity<> (suggestions, HttpStatus.OK);
	}

}