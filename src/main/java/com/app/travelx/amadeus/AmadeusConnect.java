package com.app.travelx.amadeus;
import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.amadeus.referenceData.Locations;
import com.amadeus.exceptions.ResponseException;

enum AmadeusConnect {
    INSTANCE;
    private Amadeus amadeus;


    private AmadeusConnect() {
        this.amadeus = Amadeus
            .builder("4jtv48QYT4QL3ntVCRvhd9QAdEWduAcO", "UPaGTFAcWMvJ12jT")
            .build();
    }

    /**
     * makes api call to get the all airports in a given city
     * @param keyword passes in the name of the city with the airport
     * @return returns flight code details and city where airport located
     * @throws ResponseException if api call fails
     */
    public Location[] location(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params
            .with("keyword", keyword)
            .and("subType", Locations.AIRPORT));
    }

    /**
     * makes api call to get available flights
     * @param origin city name of the departing airport
     * @param destination city name of the arrival airport
     * @param departDate depart date in the format of yyyy-mm-dd
     * @param adults number of passengers
     * @return array of available flights from api call
     * @throws ResponseException
     */
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