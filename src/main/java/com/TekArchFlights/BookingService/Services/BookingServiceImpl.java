package com.TekArchFlights.BookingService.Services;

import com.TekArchFlights.BookingService.DTOs.BookingDTO;
import com.TekArchFlights.BookingService.DTOs.FlightDTO;
import com.TekArchFlights.BookingService.DTOs.UserDTO;
import com.TekArchFlights.BookingService.Services.Interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${datastore.service.url}")
    private String datastoreServiceUrl;

    @Autowired
    public BookingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public BookingDTO createBooking(BookingDTO booking) {
        // Fetch user details
        UserDTO userDTO = restTemplate.getForObject(datastoreServiceUrl + "/users/" + booking.getUser().getUserId(), UserDTO.class);

        // Fetch flight details
        FlightDTO flightDTO = restTemplate.getForObject(datastoreServiceUrl + "/flights/" + booking.getFlight().getFlightid(), FlightDTO.class);

        // Populate the BookingDTO
        booking.setUser(userDTO);
        booking.setFlight(flightDTO);

        // Save booking
        ResponseEntity<BookingDTO> response = restTemplate.postForEntity(
                datastoreServiceUrl + "/bookings",
                booking,
                BookingDTO.class
        );
        return response.getBody();
    }


    @Override
    public BookingDTO getBookingById(Long bookingId) {
        try {
            // Fetch the booking from datastore
            BookingDTO responseDTO = restTemplate.getForObject(
                    datastoreServiceUrl +"/" + "bookings"+ "/" + bookingId,
                    BookingDTO.class
            );
            return responseDTO;  // Return the populated response
        } catch (HttpClientErrorException e) {
            // Handle the error and log it
            System.out.println("Error during getBookingById: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        try {
            // Fetch all bookings from the datastore service
            ResponseEntity<List<BookingDTO>> responseEntity = restTemplate.exchange(
                    datastoreServiceUrl + "/"  + "bookings",  // Ensure this is correct endpoint
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BookingDTO>>() {}
            );
            return responseEntity.getBody();  // Return the list of bookings
        } catch (HttpClientErrorException e) {
            // Handle the error and log it
            System.out.println("Error during getAllBookings: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteBooking(Long bookingId) {
        try {
            // Send DELETE request to remove the booking
            restTemplate.delete(datastoreServiceUrl  + "/" +"bookings" + bookingId);
        } catch (HttpClientErrorException e) {
            // Handle the error and log it
            System.out.println("Error during deleteBooking: " + e.getMessage());
            throw e;
        }
    }
}
