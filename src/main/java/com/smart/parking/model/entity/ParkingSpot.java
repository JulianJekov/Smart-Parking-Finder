package com.smart.parking.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_spots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private int totalSpots;

    @Column(nullable = false)
    private int availableSpots;

    @Column(nullable = false)
    private double pricePerHour;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
