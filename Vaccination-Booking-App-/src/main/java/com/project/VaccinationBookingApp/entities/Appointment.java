package com.project.VaccinationBookingApp.entities;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    @Column(unique = true)
    private String title;

    @NotEmpty
    @Column(length = 1000)
    private String description;

    private boolean share;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "USER_EMAIL"))
    private User user;

    public Appointment() {

    }

    public Appointment(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Appointment(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
