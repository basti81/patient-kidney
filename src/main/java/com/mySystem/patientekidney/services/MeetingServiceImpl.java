package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Meeting;
import com.mySystem.patientekidney.models.repositories.MeetingRepository;
import com.mySystem.patientekidney.services.interfaces.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;
    public MeetingServiceImpl(MeetingRepository meetingRepository){
        this.meetingRepository = meetingRepository;
    }

    @Override
    public List<Meeting> findAllByIdPatient(Long id) {
        return meetingRepository.findAllByIdPatient(id);
    }

    @Override
    public List<Meeting> findAllByIdWorker(Long id) {
        return meetingRepository.findAllByIdWorker(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return meetingRepository.existsById(id);
    }

    @Override
    public List<Meeting> findAllMeeting() {
        return meetingRepository.findAll();
    }

    @Override
    public Meeting saveMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Override
    public Optional<Meeting> getMeetingById(Long idMeeting) {
        return Optional.of(meetingRepository.getById(idMeeting));
    }

    @Override
    public void deleteMeetingById(Long idMeeting) {

    }

    @Override
    public void deleteAllMeeting() {

    }

    @Override
    public List<Meeting> saveAllMeeting(List<Meeting> meetings) {
        return null;
    }
}
