package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Meeting;

import java.util.List;
import java.util.Optional;

public interface MeetingService {
    List<Meeting> findAllByIdPatient(Long id);
    List<Meeting> findAllByIdWorker(Long id);
    Boolean existsById(Long id);
    List<Meeting> findAllMeeting();
    Meeting saveMeeting (Meeting meeting);
    Optional<Meeting> getMeetingById(Long idMeeting);
    void deleteMeetingById(Long idMeeting);
    void deleteAllMeeting();
    List<Meeting> saveAllMeeting(List<Meeting> meetings);
}
