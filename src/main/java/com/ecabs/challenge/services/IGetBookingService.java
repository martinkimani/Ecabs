/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.services;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.model.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author martin
 */
public interface IGetBookingService {
    List<Booking> listAll();
    
    Optional<Booking> findById(UUID id);
}
