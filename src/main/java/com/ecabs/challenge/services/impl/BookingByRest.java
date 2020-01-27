/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.services.impl;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.model.Response;
import com.ecabs.challenge.repository.BookingRepository;
import com.ecabs.challenge.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class BookingByRest implements IBookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Response addBooking(Booking booking) {
        bookingRepository.save(booking);
        return new Response(0, "Booking created");
    }

    @Override
    public Response editBooking(Booking booking) {
        bookingRepository.save(booking);
        return new Response(0, "Booking updated");
    }

    @Override
    public Response deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
        return new Response(0, "Booking deleted");
    }

}
