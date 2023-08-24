package com.carTracker.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.carTracker.Models.Car;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class CarController {
	private Car car = new Car();
	 private final SimpMessagingTemplate messagingTemplate;
	

	    private final Environment environment;
	    
	    @Autowired
	    public CarController( Environment environment,SimpMessagingTemplate messagingTemplate) {
	       
	        this.environment = environment;
	        this.messagingTemplate = messagingTemplate;
	    }
	    
	    @GetMapping("/directions")
	    public String showDirectionsPage(Model model) {
	        String googleMapsApiKey = environment.getProperty("google.maps.api.key");
	        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
	        return "directions";
	    }
	 
    @GetMapping("/")
    public String getCarPosition(Model model) {
        model.addAttribute("car", car);
        return "car-position";
    }

    @PostMapping("/destination")
    public String setDestination(@RequestParam("destination") String destination) {
        car.setDestination(destination);
        // Code to calculate the path and update the car's position in real-time

        // Send the updated position to subscribed clients
        String carTopic = "car1";
        messagingTemplate.convertAndSend("/topic/car-position/" + carTopic, car);

        return "redirect:/";
    }
}
