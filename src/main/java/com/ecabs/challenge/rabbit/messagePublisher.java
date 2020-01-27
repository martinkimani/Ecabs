/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.rabbit;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.util.AppConstants;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class messagePublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public messagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String routing_key, Booking booking) {
        try {
            this.rabbitTemplate.convertAndSend(AppConstants.MESSAGE_EXCHANGE, routing_key, (Object) booking);
        } catch (AmqpException e) {
            System.out.println(String.format("Failed to publish message - error : %s ", e.getMessage()));
        }
    }
}
