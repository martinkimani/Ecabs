/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.services.impl;

import com.ecabs.challenge.model.Booking;
import com.ecabs.challenge.repository.BookingRepository;
import com.ecabs.challenge.services.IGetBookingService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class GetBookingServiceImpl implements IGetBookingService {

    @Autowired
    BookingRepository booking;

    @Override
    public List<Booking> listAll() {
        return booking.findAll();
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return Optional.of(booking.getOne(id));
    }

}
