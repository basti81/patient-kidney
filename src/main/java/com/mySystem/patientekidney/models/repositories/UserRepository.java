package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Boolean existsByRut(String rut);
    Boolean existsByMail(String mail);
}
