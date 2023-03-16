package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Record;
import com.mySystem.patientekidney.models.repositories.RecordRepository;
import com.mySystem.patientekidney.services.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;
    public RecordServiceImpl(RecordRepository recordRepository){
        this.recordRepository = recordRepository;
    }

    @Override
    public Record saveRecord(Record record) {
        return null;
    }
}
