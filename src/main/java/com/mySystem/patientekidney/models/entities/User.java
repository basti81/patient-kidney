package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;
    @Column(name = "rut", columnDefinition = "varchar(150)")
    private String rut;
    @Column(columnDefinition = "varchar(100)")
    private String name;
    @Column(name="last_name" , columnDefinition = "varchar(100)")
    private String lastName;
    @Column(unique = true, nullable = false)
    private String mail;

   /* private String password;
    private String img;*/
   @NonNull
    @Column(name = "start_date")
    private Instant startDate;
    private boolean enabled;

    public User() {
    }

    public User(String rut, String name, String lastName, String mail, boolean enabled) {
        this.rut = rut;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.enabled = enabled;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getForename(){
        String[] newName = name.trim().split("\\s+");
        return newName[0];
    }


}
