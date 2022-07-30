package com.project.VaccinationBookingApp.repositories;

import java.util.List;
import com.project.VaccinationBookingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{


	  List<User> findByUsernameLike(String s);
	  User findByEmail(String email);
}
