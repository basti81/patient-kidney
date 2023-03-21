package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.User;

import java.util.Optional;

public interface UserService{
    Boolean existsByMail(String mail);
    Boolean existsByRut(String rut);
    User saveUser(User user);
    Optional<User> getUserById(Long idUser);
    void deleteUserById(Long idUser);

}
