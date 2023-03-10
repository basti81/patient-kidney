package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.Patiente;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PatienteRepositoryTest {

    @Autowired
    private PatienteRepository patienteRepository;

    /*public PatienteRepositoryTest(PatienteRepository patienteRepository){
        this.patienteRepository = patienteRepository;
    }*/

    @Test
    public void givenPatiente_whenSavePatiente_thenSavedPatiente(){
        //Given - precondition or setup
        Patiente patiente = new Patiente("20.088.634-8","Bastian Astudillo","Fonasa");
        //Patiente savedPatiente = patienteRepository.save(patiente);
        //assertThat(patiente, CoreMatchers.is(savedPatiente));
        System.out.println(patiente.toString());

    }
}