/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.util;

import java.util.Random;

/**
 *
 * @author martin
 */
public class AppConstants {

    // RabbitMQ constansts
    public static final String MESSAGE_EXCHANGE = "messageExchange";
    public static final String BOOKING_EXCHANGE = "bookingExchange";
    public static final String MessageAuditQueue = "MessageAuditQueue";
    public static final String BookingAddQueue = "BookingAddQueue";
    public static final String BookingEditQueue = "BookingEditQueue";
    public static final String BookingDeleteQueue = "BookingDeleteQueue";
    public static final String ROUTING_KEY_EXCHANGES = "messages.*";
    public static final String ROUTING_KEY_MESSAGES = "messages.*";
    public static final String ROUTING_KEY_ADDBOOKING = "*.add";
    public static final String ROUTING_KEY_EDITBOOKING = "*.edit";
    public static final String ROUTING_KEY_DELETEBOOKING = "*.delete";
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String generateToken() {
        String s = "";
        Random random = new Random();
        int randomLen = 1 + random.nextInt(19);
        for (int i = 0; i < randomLen; i++) {
            char c = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
            s += c;
        }
        return s;
    }
}
