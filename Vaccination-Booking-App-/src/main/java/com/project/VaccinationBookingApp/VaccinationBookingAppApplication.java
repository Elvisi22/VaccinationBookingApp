package com.project.VaccinationBookingApp;

import com.project.VaccinationBookingApp.entities.User;
import com.project.VaccinationBookingApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class VaccinationBookingAppApplication implements CommandLineRunner{
    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(VaccinationBookingAppApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        {
            User newAdmin = new User("admin@mail.com", "Admin", "123456");
            userService.createAdmin(newAdmin);
        }
    }


}

