package com.TekArchFlights.BookingService.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    // For the Request

    @NotNull
    private UserDTO user;

    @NotNull// Nested user object
    private FlightDTO flight; //
    // Nested flight object     // for request: flightId to associate with booking
    private String status;      // for request: status of booking
    private int seatsBooked;    // for request: number of seats booked

    // For the Response
    private Long bookingId;     // for response: generated bookingId
    private String userUsername;  // for response: populated from User entity
    private String userEmail;    // for response: populated from User entity
    private String flightNumber;  // for response: populated from Flight entity
    private LocalDateTime createdAt; // for response: createdAt from database
    private LocalDateTime updatedAt; // for response: updatedAt from database



}
