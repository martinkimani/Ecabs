/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.web;

import com.ecabs.challenge.exception.ResourceNotFoundException;
import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.services.BookingServiceFactory;
import com.ecabs.challenge.services.IGetBookingService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author martin
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    IGetBookingService getBookings;

    @Autowired
    BookingServiceFactory bookingService;

    @GetMapping("/bookings")
    public ResponseEntity getAllBookings() {
        return new ResponseEntity(getBookings.listAll(), HttpStatus.OK);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity getBookingById(@PathVariable(value = "id") UUID id) {
        return getBookings.findById(id)
                .map(response -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .body(response);
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
    }

    @PostMapping("/bookings/{type}")
    public ResponseEntity addBooking(@RequestBody Booking booking, @PathVariable(value = "type") Integer type) {
        return new ResponseEntity(bookingService.getByType(type).addBooking(booking), HttpStatus.OK);
    }

    @PutMapping("/bookings/{id}/{type}")
    public ResponseEntity editBooking(@RequestBody Booking booking, @PathVariable(value = "type") Integer type,
            @PathVariable(value = "id") UUID id) {
        Optional<Booking> getbooking = getBookings.findById(id);
        getbooking.orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        return new ResponseEntity(bookingService.getByType(type).editBooking(booking), HttpStatus.OK);
    }

    @DeleteMapping("/bookings/{id}/{type}")
    public ResponseEntity deleteBooking(@PathVariable(value = "type") Integer type,
            @PathVariable(value = "id") UUID id) {
        Optional<Booking> booking = getBookings.findById(id);
        booking.orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        return new ResponseEntity(bookingService.getByType(type).deleteBooking(booking.get()), HttpStatus.OK);
    }
}
