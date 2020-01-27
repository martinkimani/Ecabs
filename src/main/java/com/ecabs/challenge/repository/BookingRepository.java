/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.repository;

import com.ecabs.challenge.model.Booking;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author martin
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    
}
