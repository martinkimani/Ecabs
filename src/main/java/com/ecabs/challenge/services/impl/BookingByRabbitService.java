/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.services.impl;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.model.Response;
import com.ecabs.challenge.rabbit.messagePublisher;
import com.ecabs.challenge.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class BookingByRabbitService implements IBookingService {

    @Autowired
    messagePublisher pub;

    @Override
    public Response addBooking(Booking booking) {
        pub.send("messages.add", booking);
        return new Response(0, "booking sent to creation queue");
    }

    @Override
    public Response editBooking(Booking booking) {
        pub.send("messages.edit", booking);
        return new Response(0, "booking sent to updating queue");
    }

    @Override
    public Response deleteBooking(Booking booking) {
        pub.send("messages.delete", booking);
        return new Response(0, "booking sent to deletion queue");
    }

}
