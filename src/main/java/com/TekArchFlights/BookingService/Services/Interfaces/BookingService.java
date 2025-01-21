package com.TekArchFlights.BookingService.Services.Interfaces;

import com.TekArchFlights.BookingService.DTOs.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO requestDTO);
    BookingDTO getBookingById(Long bookingId);
    List<BookingDTO> getAllBookings();  // New method to get all bookings
    void deleteBooking(Long bookingId);  // New method to delete a booking
}
