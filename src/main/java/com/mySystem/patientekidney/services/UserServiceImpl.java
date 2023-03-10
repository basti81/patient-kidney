package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.User;
import com.mySystem.patientekidney.models.repositories.UserRepository;
import com.mySystem.patientekidney.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long idUser) {
        return Optional.empty();
    }

    @Override
    public void deleteUserById(Long idUser) {

    }
}
