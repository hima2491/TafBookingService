package com.TekArchFlights.BookingService.Controller;

import com.TekArchFlights.BookingService.DTOs.BookingDTO;
import com.TekArchFlights.BookingService.Services.Interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        // Call the service to create a booking
        BookingDTO responseDTO = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(responseDTO);  // Returns the responseDTO with populated data
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        BookingDTO responseDTO = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(responseDTO);  // Returns the booking data with all details
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);  // Returns a list of all bookings
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);  // Deletes the booking via service
        System.out.println("Deleted booking successfully for ID: " + bookingId);
        return ResponseEntity.noContent().build();  // Returns a 204 No Content response
    }
}
