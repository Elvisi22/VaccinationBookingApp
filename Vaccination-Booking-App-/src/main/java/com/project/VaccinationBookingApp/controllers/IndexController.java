package com.project.VaccinationBookingApp.controllers;

import com.project.VaccinationBookingApp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {
	@Autowired 
	private AppointmentService appointmentService;
	@GetMapping("/")
    public String showIndex() {
        return "index";
    }



    @GetMapping("/login")
    public String showLogin() {

        return "views/login";
    }
    
  
}
