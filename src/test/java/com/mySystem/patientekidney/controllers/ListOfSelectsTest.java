package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.librery.StatePrevision;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ListOfSelectsTest {

    private StatePrevision statePrevision;


    @Test
    public void givenPatient_whenNewTest_thenListEnums(){
        System.out.println(Arrays.stream(StatePrevision.values()).toList());



    }


}