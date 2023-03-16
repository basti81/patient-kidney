package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Record;

import java.util.Optional;

public interface RecordService {
    Record saveRecord (Record record);
    Boolean existsById(Long id);
    Optional<Record> getRecordById(Long id);

}
