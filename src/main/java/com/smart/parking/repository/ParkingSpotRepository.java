package com.smart.parking.repository;

import com.smart.parking.model.dto.parkingSpot.ParkingSpotViewDTO;
import com.smart.parking.model.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByAvailableSpotsGreaterThan(int availableSpots);
    List<ParkingSpot> findByOwnerId(Long ownerId);
}
