/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author martin
 */
@Entity
@Table(name = "trip_waypoints")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
public class TripWaypoint implements Serializable {
    
    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false )
    private UUID tripWayPoint;
    
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    private boolean lastStop;
    
    @NotBlank
    private String locality;
    
    @Column(nullable = false)
    private double lat;
    
    @Column(nullable = false)
    private double lng;
    
    @Column(nullable = false)
    @LastModifiedDate
    private Instant tripWayPointTimestamp;
}
