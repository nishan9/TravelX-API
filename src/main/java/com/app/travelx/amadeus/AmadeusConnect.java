package com.app.travelx.amadeus;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.amadeus.referenceData.Locations;
import com.amadeus.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

enum AmadeusConnect {
    INSTANCE;
    private Amadeus amadeus;

    @Value("${amadeus.id}")
    private String id;

    @Value("${amadeus.secret}")
    private String secret;


    private AmadeusConnect() {
        this.amadeus = Amadeus
            .builder(id, secret)
            .build();
    }

    public Location[] location(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params
            .with("keyword", keyword)
            .and("subType", Locations.AIRPORT));
    }

    public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults) throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", origin)
                        .and("destinationLocationCode", destination)
                        .and("departureDate", departDate)
                        .and("adults", adults)
                        .and("nonStop", true)
                        .and("max", 10));
    }
}