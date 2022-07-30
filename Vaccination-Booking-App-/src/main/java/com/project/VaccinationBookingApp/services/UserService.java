package com.project.VaccinationBookingApp.services;

import java.util.ArrayList;
import java.util.List;

import com.project.VaccinationBookingApp.entities.Role;
import com.project.VaccinationBookingApp.entities.User;
import com.project.VaccinationBookingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
    	user.setRoles(roles);
		userRepository.save(user);
	}

	public void createAdmin(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	public User findOne(String email) {
		return userRepository.findByEmail(email);
	}

	public boolean isUserPresent(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findByUsername(String name) {
		return userRepository.findByUsernameLike("%" + name + "%");
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

}
