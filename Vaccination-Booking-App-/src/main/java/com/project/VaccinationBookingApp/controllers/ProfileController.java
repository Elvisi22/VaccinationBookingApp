package com.project.VaccinationBookingApp.controllers;

import java.security.Principal;


import com.project.VaccinationBookingApp.entities.User;
import com.project.VaccinationBookingApp.services.AppointmentService;
import com.project.VaccinationBookingApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class ProfileController {
	   @Autowired
	    private AppointmentService appointmentService;

	    @Autowired
	    private UserService userService;

	    @GetMapping("/profile")
	    public String showProfilePage(Model model, Principal principal) {

	        String email = principal.getName();
	        User user = userService.findOne(email);

	        model.addAttribute("appointments", appointmentService.findAll());
			//model.addAttribute("appointments", appointmentService.findUserAppointment(user));


	        return "profile";
	    }
}
