package com.smart.parking.controller;

import com.smart.parking.model.dto.parkingSpot.ParkingSpotCreateDTO;
import com.smart.parking.model.dto.parkingSpot.ParkingSpotEditDTO;
import com.smart.parking.model.dto.parkingSpot.ParkingSpotViewDTO;
import com.smart.parking.model.entity.CustomUserDetails;
import com.smart.parking.model.entity.ParkingSpot;
import com.smart.parking.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/business")
public class BusinessController {

    private final ParkingSpotService parkingSpotService;

    public BusinessController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @ModelAttribute
    public ParkingSpotCreateDTO getParkingSpotCreateDTO() {
        return new ParkingSpotCreateDTO();
    }
    @ModelAttribute
    public ParkingSpotEditDTO getParkingSpotEditDTO() {
        return new ParkingSpotEditDTO();
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long ownerId = userDetails.getId();
        List<ParkingSpotViewDTO> parkingSpotViews = parkingSpotService.getParkingSpotsByOwner(ownerId);
        model.addAttribute("parkingSpotViews", parkingSpotViews);
        return "business-dashboard";
    }

    @PostMapping("/parking-spots")
    public String addParkingSpot(@Valid ParkingSpotCreateDTO parkingSpotCreateDTO,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal CustomUserDetails userDetails,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            Long ownerId = userDetails.getId();
            List<ParkingSpotViewDTO> parkingSpotViews = parkingSpotService.getParkingSpotsByOwner(ownerId);
            model.addAttribute("parkingSpotViews", parkingSpotViews);
            return "business-dashboard";
        }

        Long ownerId = userDetails.getId();
        parkingSpotService.addParkingSpot(parkingSpotCreateDTO, ownerId);
        return "redirect:/business/dashboard";
    }

    @GetMapping("/parking-spots/{id}/edit")
    public String editParkingSpot(@PathVariable("id") Long id, Model model) {
        ParkingSpotEditDTO parkingSpotEditDTO = parkingSpotService.getParkingSpotEditDtoById(id);
        model.addAttribute("parkingSpotEditDTO", parkingSpotEditDTO);
        return "edit-parking-spot";
    }

    @PatchMapping("/parking-spots/{id}/edit")
    public String updateParkingSpot(@PathVariable Long id,
                                    @Valid ParkingSpotEditDTO parkingSpotEditDTO,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-parking-spot";
        }

        parkingSpotService.updateParkingSpot(id, parkingSpotEditDTO);
        return "redirect:/business/dashboard";
    }

    @DeleteMapping("/parking-spots/{id}/delete")
    public String deleteParkingSpot(@PathVariable Long id) {
        parkingSpotService.deleteParkingSpot(id);
        return "redirect:/business/dashboard";
    }
}
