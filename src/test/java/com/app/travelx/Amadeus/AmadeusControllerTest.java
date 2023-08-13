package com.app.travelx.Amadeus;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.app.travelx.amadeus.AmadeusController;
import com.app.travelx.amadeus.AmadeusService;
import com.app.travelx.amadeus.SuggestionsModel;
import com.app.travelx.amadeus.FlightInfoModel;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AmadeusControllerTest {

    @InjectMocks
    private AmadeusController amadeusController;

    @Mock
    private AmadeusService amadeusService;
    @Autowired
    private MockMvc mockMVC;
    @Autowired
    private ObjectMapper mapper;
  
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFlights() throws ResponseException {

        ResponseEntity<ArrayList<FlightInfoModel>> response = amadeusController.getFlights("LHR", "CDG", "2023-09-09", "1");
        ArrayList<FlightInfoModel> responseArray = response.getBody();
        if (!responseArray.isEmpty()) {
            FlightInfoModel sampleFlight = responseArray.get(0);
            Field[] fields = sampleFlight.getClass().getDeclaredFields();
            String[] validateFields = new String[9];
            assertThat(fields[0].getName()).isEqualTo("departLocation");
            assertThat(fields[1].getName()).isEqualTo("arrivalLocation");
            assertThat(fields[2].getName()).isEqualTo("departTime");
            assertThat(fields[3].getName()).isEqualTo("arrivalTime");
            assertThat(fields[4].getName()).isEqualTo("airline");
            assertThat(fields[5].getName()).isEqualTo("currerncy");
            assertThat(fields[6].getName()).isEqualTo("totalPrice");
            assertThat(fields[7].getName()).isEqualTo("availableSeats");
            assertThat(fields[8].getName()).isEqualTo("duration");
            for (int i = 0; i<responseArray.size(); i++){
                assertThat(responseArray.get(0).getArrivalLocation()).isEqualTo("CDG");
                assertThat(responseArray.get(0).getDepartLocation()).isEqualTo("LHR");
                assertThat(responseArray.get(0).getAvailableSeats()).isGreaterThanOrEqualTo(1);
            }
        }

    }

    @Test
    public void testLocations() throws JsonProcessingException, ResponseException, Exception {
        SuggestionsModel testLocation = new SuggestionsModel("HEATHROW", "LHR", "LONDON", 51.4775, -0.46138);

        ResponseEntity<ArrayList<SuggestionsModel>> response = amadeusController.locations("LHR");
        ArrayList<SuggestionsModel> responseArray = response.getBody();
        assertThat(responseArray.size()).isEqualTo(1);
        assertThat(responseArray.get(0).getName()).isEqualTo(testLocation.getName());
        assertThat(responseArray.get(0).getCity()).isEqualTo(testLocation.getCity());
        assertThat(responseArray.get(0).getLabel()).isEqualTo(testLocation.getLabel());
        assertThat(responseArray.get(0).getLatitude()).isEqualTo(testLocation.getLatitude());
        assertThat(responseArray.get(0).getLongitude()).isEqualTo(testLocation.getLongitude());
    }
**/
}
