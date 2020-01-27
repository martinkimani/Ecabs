/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author martin
 */
@Component
public class BookingServiceFactory {

    @Autowired
    @Qualifier("bookingByRabbitService")
    private IBookingService rabbitBookingSrevice;

    @Autowired
    @Qualifier("bookingByRest")
    private IBookingService restBookingSrevice;

    public IBookingService getByType(Integer type) {
        if (type == 0) {
            return restBookingSrevice;
        } else {
            return rabbitBookingSrevice;
        }
    }
}
