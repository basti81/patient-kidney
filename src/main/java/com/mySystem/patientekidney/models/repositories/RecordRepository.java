package com.mySystem.patientekidney.models.repositories;
import com.mySystem.patientekidney.models.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {
}
