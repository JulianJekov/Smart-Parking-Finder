package com.smart.parking.service.impl;

import com.smart.parking.model.dto.parkingSpot.ParkingSpotCreateDTO;
import com.smart.parking.model.dto.parkingSpot.ParkingSpotEditDTO;
import com.smart.parking.model.dto.parkingSpot.ParkingSpotViewDTO;
import com.smart.parking.model.entity.ParkingSpot;
import com.smart.parking.model.entity.User;
import com.smart.parking.model.enums.ParkingSpotStatus;
import com.smart.parking.repository.ParkingSpotRepository;
import com.smart.parking.repository.UserRepository;
import com.smart.parking.service.ParkingSpotService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ParkingSpotViewDTO> getParkingSpotsByOwner(Long ownerId) {
        return parkingSpotRepository.findByOwnerId(ownerId)
                .stream()
                .map(parkingSpot -> modelMapper.map(parkingSpot, ParkingSpotViewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addParkingSpot(ParkingSpotCreateDTO parkingSpotCreateDTO, Long ownerId) {
        Optional<User> owner = userRepository.findById(ownerId);
        if (owner.isEmpty()) {
            throw new RuntimeException("Owner not found");
        }
        ParkingSpot parkingSpot = modelMapper.map(parkingSpotCreateDTO, ParkingSpot.class);
        parkingSpot.setAvailableSpots(parkingSpot.getTotalSpots());
        parkingSpot.setOwner(owner.get());
        parkingSpot.setReservations(new ArrayList<>());
        parkingSpot.setStatus(ParkingSpotStatus.ACTIVE);

        parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public ParkingSpotEditDTO getParkingSpotEditDtoById(Long id) {
        Optional<ParkingSpot> parkingSpot = getParkingSpot(id);
        return modelMapper.map(parkingSpot.get(), ParkingSpotEditDTO.class);
    }

    @Override
    public void updateParkingSpot(Long id, ParkingSpotEditDTO parkingSpotEditDTO) {
        Optional<ParkingSpot> parkingSpot = getParkingSpot(id);
        modelMapper.map(parkingSpotEditDTO, parkingSpot.get());
        parkingSpotRepository.save(parkingSpot.get());
    }

    @Override
    public void deleteParkingSpot(Long id) {
        parkingSpotRepository.deleteById(id);
    }

    private Optional<ParkingSpot> getParkingSpot(Long id) {
        Optional<ParkingSpot> parkingSpot = parkingSpotRepository.findById(id);
        if (parkingSpot.isEmpty()) {
            throw new RuntimeException("ParkingSpot not found");
        }
        return parkingSpot;
    }
}
