package com.app.travelx.amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value="api", produces = MediaType.APPLICATION_JSON_VALUE)

public class AmadeusController {

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
	public FlightOfferSearch[] onewayflightsnonstop (@RequestParam(required=true) String origin,
													 @RequestParam(required=true) String destination,
													 @RequestParam(required=true) String departDate,
													 @RequestParam(required=true) String adults)
													 throws ResponseException {
		return AmadeusConnect.INSTANCE.onewayflightsnonstop(origin, destination, departDate, adults);
	}
}