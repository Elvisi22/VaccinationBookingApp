package com.project.VaccinationBookingApp.services;

import java.util.List;

import com.project.VaccinationBookingApp.entities.Appointment;
import com.project.VaccinationBookingApp.entities.User;
import com.project.VaccinationBookingApp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	public void addAppointment(Appointment appointment, User user) {
		appointment.setUser(user);
		appointmentRepository.save(appointment);
	}

	public List<Appointment> findUserAppointment(User user) {
		return appointmentRepository.findByUser(user);
	}

	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}



	public Appointment findById(Long id) {
		return appointmentRepository.findById1(id);
	}

	public List<Appointment> findByTitle(String title) {
		return appointmentRepository.findByTitleLike(title);
	}

	public void delete(Long id) {
		appointmentRepository.deleteById(id);
	}


}
