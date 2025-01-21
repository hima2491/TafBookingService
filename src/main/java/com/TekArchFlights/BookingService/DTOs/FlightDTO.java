package com.TekArchFlights.BookingService.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDTO {

    private Long flightid;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private Date departureTime;
    private Date arrivalTime;
    private String aircraftType;
    private int availableSeats;
    private String destination;
}
