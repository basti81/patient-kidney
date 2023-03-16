package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.services.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordService recordService;
    public RecordServiceImpl(RecordService recordService){
        this.recordService = recordService;
    }

    @Override
    public Record findById(Long id) {
        return recordService.findById(id);
    }
}
