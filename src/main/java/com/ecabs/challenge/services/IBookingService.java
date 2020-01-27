/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.services;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.model.Response;
import java.util.UUID;

/**
 *
 * @author martin
 */
public interface IBookingService {
        
    Response addBooking(Booking booking);
    
    Response editBooking(Booking booking);
    
    Response deleteBooking(Booking booking);
}
