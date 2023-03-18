package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StateMeeting;
import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table( name= "meeting")
public class Meeting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeeting;
    @Column(name = "date_time_meeting")
    private LocalDateTime dateTimeMeeting;
    @Column(name = "state_meeting")
    private StateMeeting stateMeeting;
    @Column(name = "note_meeting",columnDefinition = "TEXT")
    private String noteMeeting;
    @Column(name = "meeting_date")
    private Instant meetingDate;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    public Meeting(){

    }


    public Long getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(Long idMeeting) {
        this.idMeeting = idMeeting;
    }

    public LocalDateTime getDateTimeMeeting() {
        return dateTimeMeeting;
    }

    public void setDateTimeMeeting(LocalDateTime dateTimeMeeting) {
        this.dateTimeMeeting = dateTimeMeeting;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateMeeting() {
        return dateTimeMeeting;
    }

    public void setDateMeeting(LocalDateTime dateTimeMeeting) {
        this.dateTimeMeeting = dateTimeMeeting;
    }

    public StateMeeting getStateMeeting() {
        return stateMeeting;
    }

    public void setStateMeeting(StateMeeting stateMeeting) {
        this.stateMeeting = stateMeeting;
    }

    public String getNoteMeeting() {
        return noteMeeting;
    }

    public void setNoteMeeting(String noteMeeting) {
        this.noteMeeting = noteMeeting;
    }

    public Instant getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Instant meetingDate) {
        this.meetingDate = meetingDate;
    }
}
