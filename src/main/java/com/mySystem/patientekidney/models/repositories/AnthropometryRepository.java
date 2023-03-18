package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.Anthropometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnthropometryRepository extends JpaRepository<Anthropometry, Long> {
}
