package com.smart.parking.service;

import com.smart.parking.model.dto.parkingSpot.ParkingSpotCreateDTO;
import com.smart.parking.model.dto.parkingSpot.ParkingSpotEditDTO;
import com.smart.parking.model.dto.parkingSpot.ParkingSpotViewDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ParkingSpotService {
    List<ParkingSpotViewDTO> getParkingSpotsByOwner(Long ownerId);

    void addParkingSpot(ParkingSpotCreateDTO parkingSpotCreateDTO, Long ownerId);

    ParkingSpotEditDTO getParkingSpotEditDtoById(Long id);

    void updateParkingSpot(Long id, @Valid ParkingSpotEditDTO parkingSpotEditDTO);

    void deleteParkingSpot(Long id);
}
