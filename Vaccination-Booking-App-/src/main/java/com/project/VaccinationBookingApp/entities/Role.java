package com.project.VaccinationBookingApp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Role {

    @Id
    @Size(max = 128)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}