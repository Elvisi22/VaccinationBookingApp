package com.project.VaccinationBookingApp.repositories;

import java.util.List;

import com.project.VaccinationBookingApp.entities.Appointment;
import com.project.VaccinationBookingApp.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByUser(User user);

	@Query(value = "SELECT u FROM Appointment u where u.title=?1 and u.share=1")
	List<Appointment> findByTitleLike(String s);
	@Query(value ="SELECT u FROM Appointment u where u.id=?1")
	Appointment findById1(Long id);


}
