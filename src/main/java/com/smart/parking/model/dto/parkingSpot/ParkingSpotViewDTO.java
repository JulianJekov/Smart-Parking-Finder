package com.smart.parking.model.dto.parkingSpot;

import lombok.Data;

@Data
public class ParkingSpotViewDTO {
    private Long id;
    private Long ownerId;
    private String locationName;
    private int totalSpots;
    private int availableSpots;
    private double pricePerHour;
}
