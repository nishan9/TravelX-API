package com.app.travelx.amadeus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String duration;

    public FlightInfoModel(String departLocation, String arrivalLocation, String departTime, String arrivalTime, String airline, String currency, double totalPrice, int availableSeats, String duration) {
        this.departLocation = departLocation;
        this.arrivalLocation = arrivalLocation;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.currency = currency;
        this.totalPrice = totalPrice;
        this.availableSeats = availableSeats;
        this.duration = duration;
    }
}
