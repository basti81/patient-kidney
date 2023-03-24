package com.mySystem.patientekidney.librery;

public enum StateRace {
    CAUCASIAN("Caucasian"),
    AFRO_AMERICAN("AfroAmerican"),
    JAPANESE("Japanese"),
    CHINESE("Chinese");

    private final String displayValue;
    private StateRace(String displayValue){
        this.displayValue = displayValue;
    }
    public String getDisplayValue(){
        return displayValue;
    }
}
