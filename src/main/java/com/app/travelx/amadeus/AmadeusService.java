package com.app.travelx.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;

public interface AmadeusService {
    Location[] location(String keyword) throws ResponseException;
    FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults) throws ResponseException;
}
