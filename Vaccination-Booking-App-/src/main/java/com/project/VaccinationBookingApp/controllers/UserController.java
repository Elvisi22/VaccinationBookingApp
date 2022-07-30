package com.project.VaccinationBookingApp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.project.VaccinationBookingApp.entities.User;
import com.project.VaccinationBookingApp.services.AppointmentService;
import com.project.VaccinationBookingApp.services.UserService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/users")
	private String listUsers(Model model, @RequestParam(defaultValue = "") String name) {
		model.addAttribute("users", userService.findByUsername(name));
		return "views/list";
	}

	@GetMapping("/editUser/{email}")
	public String showUpdateForm(@PathVariable("email") String email, Model model) {
		User user = userService.findOne(email);
		model.addAttribute("user", user);
		return "views/update-user";
	}

	@PostMapping("/updateUser/{email}")
	public String updateUser(@PathVariable("email") String email, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setEmail(email);
			return "views/update-user";
		}
		userService.createUser(user);
		model.addAttribute("users", userService.findAll());
		return "views/list";
	}

	@GetMapping("/deleteUser/{email}")
	public String deleteUser(@PathVariable("email") String email, Model model) {
		User user = userService.findOne(email);
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "views/list";
	}
	
	@GetMapping("/editUserByUser")
	public String showUpdateFormByUser(Model model, HttpSession sessionl) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userService.findOne(currentPrincipalName);		
		model.addAttribute("user", user);
		return "views/update-user-by-user";
	}

	@PostMapping("/updateUserByUser/{email}")
	public String updateUserByUser(@PathVariable("email") String email, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setEmail(email);
			return "views/update-user-by-user";
		}
		userService.createUser(user);
		model.addAttribute("appointments",appointmentService.findUserAppointment(user));
		return "views/profile";
	}








	}


	


