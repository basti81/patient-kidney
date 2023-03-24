package com.mySystem.patientekidney.librery;

import org.hibernate.mapping.Array;

import java.util.Arrays;

public enum StatePrevision {
    FONASA("Fonasa"),
    ISAPRE("Isapre");
    private final String displayValue;
    private StatePrevision(String displayValue){
        this.displayValue = displayValue;
    }
    public String getDisplayValue(){
        return displayValue;
    }


}
