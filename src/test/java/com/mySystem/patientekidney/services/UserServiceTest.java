package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.User;
import com.mySystem.patientekidney.models.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserServiceTest {

    private UserRepository userRepository;
    @Test
    public void givenUser_whenSaveUser_thenReturnSavedUser(){
        //Given - precondition or setup
    }
}