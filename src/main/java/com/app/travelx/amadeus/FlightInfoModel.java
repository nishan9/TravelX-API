package com.app.travelx.amadeus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightInfoModel {
    private String departLocation;

    private String arrivalLocation;

    private String departTime;

    private String arrivalTime;

    private String airline;

    private String currency;

    private double totalPrice;

    private int availableSeats;
}
