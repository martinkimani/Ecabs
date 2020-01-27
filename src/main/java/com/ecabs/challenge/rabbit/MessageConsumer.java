/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.rabbit;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.repository.BookingRepository;
import com.ecabs.challenge.util.AppConstants;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class MessageConsumer {

    @Autowired
    BookingRepository bookingRepository;

    @RabbitListener(queues = AppConstants.BookingAddQueue)
    public void addBooking(Booking booking, final Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        bookingRepository.save(booking);
    }

    @RabbitListener(queues = AppConstants.BookingEditQueue)
    public void editBooking(Booking booking, final Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        bookingRepository.save(booking);
    }

    @RabbitListener(queues = AppConstants.BookingDeleteQueue)
    public void deleteBooking(Booking booking, final Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        bookingRepository.delete(booking);
    }

    @RabbitListener(queues = AppConstants.MessageAuditQueue)
    public void printBooking(Booking booking, final Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicAck(tag, false);
        System.out.println("message-> " + booking.toString());
    }
}
