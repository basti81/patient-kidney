package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.User;

import java.util.Optional;

public interface UserService{
    User saveUser(User user);
    Optional<User> getUserById(Long idUser);
    void deleteUserById(Long idUser);
}
