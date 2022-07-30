package com.project.VaccinationBookingApp.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id
    @Email
    @Column(unique = true)
    @NotEmpty
    @Size(max = 128)
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 6)
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })
  private List<Role> roles;

    public User() {

    }

    public User(String email,String username,String password) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
