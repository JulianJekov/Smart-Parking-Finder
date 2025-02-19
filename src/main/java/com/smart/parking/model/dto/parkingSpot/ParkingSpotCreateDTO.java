package com.smart.parking.model.dto.parkingSpot;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ParkingSpotCreateDTO {
    @NotBlank(message = "Location name is required")
    @Size(max = 100, message = "Location name must be less than 100 characters")
    private String locationName;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be at least -90.0")
    @DecimalMax(value = "90.0", message = "Latitude must be at most 90.0")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be at least -180.0")
    @DecimalMax(value = "180.0", message = "Longitude must be at most 180.0")
    private Double longitude;

    @NotNull(message = "Total spots is required")
    @Min(value = 1, message = "Total spots must be at least 1")
    private Integer totalSpots;

    @NotNull(message = "Price per hour is required")
    @DecimalMin(value = "0.01", message = "Price per hour must be at least 0.01")
    private Double pricePerHour;
}
