package com.project.VaccinationBookingApp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.project.VaccinationBookingApp.entities.Appointment;
import com.project.VaccinationBookingApp.entities.User;
import com.project.VaccinationBookingApp.services.AppointmentService;
import com.project.VaccinationBookingApp.services.UserService;
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
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserService userService;

	@GetMapping("/appointments")
	private String listAppointment(Model model, @RequestParam(defaultValue = "") String title) {
		model.addAttribute("appointments", appointmentService.findByTitle(title));
		return "index2";
	}
	@GetMapping("/addAppointment")
	public String appointmentForm(Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		session.setAttribute("email", currentPrincipalName);
		model.addAttribute("appointment", new Appointment());
		return "addAppointment";

	}

	@PostMapping("/addAppointment")
	public String addAppointment2(@Valid Appointment appointment, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "addAppointment";
		}
		String email = (String) session.getAttribute("email");
		appointmentService.addAppointment(appointment, userService.findOne(email));

		return "redirect:/profile";
	}

	@GetMapping("/editAppointment/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Appointment appointment = appointmentService.findById(id);
		model.addAttribute("appointment", appointment);
		return "views/update-appointment";
	}

	@PostMapping("/updateAppointment/{id}")
	public String updateAppointment(@PathVariable("id") Long id, @Valid Appointment appointment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			appointment.setId(id);
			return "redirect:/profile";
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userService.findOne(currentPrincipalName);

		appointmentService.addAppointment(appointment, user);
		model.addAttribute("appointments", appointmentService.findUserAppointment(user));
		return "redirect:/profile";
	}

	@GetMapping("/deleteAppointment/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userService.findOne(currentPrincipalName);
		appointmentService.delete(id);
		model.addAttribute("appointments", appointmentService.findUserAppointment(user));
		return "views/profile";
	}

}
