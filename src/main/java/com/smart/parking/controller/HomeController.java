package com.smart.parking.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "index";
    }
}
