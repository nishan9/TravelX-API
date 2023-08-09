package com.app.travelx.Amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.app.travelx.amadeus.AmadeusController;
import com.app.travelx.amadeus.AmadeusService;
import com.app.travelx.amadeus.FlightInfoModel;
import com.app.travelx.amadeus.SuggestionsModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AmadeusControllerTest {

    @InjectMocks
    private AmadeusController amadeusController;

    @Mock
    private AmadeusService amadeusService;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFlights() throws ResponseException {
        FlightOfferSearch[] flightOfferSearches = {}; // Sample data here
        when(amadeusService.flights(any(), any(), any(), any())).thenReturn(flightOfferSearches);

        ResponseEntity<ArrayList<FlightInfoModel>> response = amadeusController.getFlights("LHR", "CDG", "2023-09-09", "1");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testLocations() throws ResponseException {
        Location[] locations = {}; // Sample data here
        when(amadeusService.location(any())).thenReturn(locations);

        ResponseEntity<ArrayList<SuggestionsModel>> response = amadeusController.locations("Keyword");
        assertEquals(200, response.getStatusCodeValue());
    }

    // Add more tests, including edge cases, unexpected behaviors, and exception scenarios
}
