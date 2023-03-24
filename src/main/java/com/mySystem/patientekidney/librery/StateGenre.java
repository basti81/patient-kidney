package com.mySystem.patientekidney.librery;

public enum StateGenre {
    MALE("Male"),
    FEMALE("Female");

    private final String displayValue;
    private StateGenre(String displayValue){
        this.displayValue = displayValue;
    }
    public String getDisplayValue(){
        return displayValue;
    }
}
