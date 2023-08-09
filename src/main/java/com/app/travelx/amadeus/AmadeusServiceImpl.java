package com.app.travelx.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;

public class AmadeusServiceImpl implements AmadeusService {

    @Override
    public Location[] location(String keyword) throws ResponseException {
        return AmadeusConnect.INSTANCE.location(keyword);
    }

    @Override
    public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults) throws ResponseException {
        return AmadeusConnect.INSTANCE.flights(origin, destination, departDate, adults);
    }
}
