package com.mySystem.patientekidney.librery;

public enum StateRole {
    ROLE_ADMIN("role_admin"),
    ROLE_PATIENT("role_patient"),
    ROLE_TENS("role_tens"),
    ROLE_ASSISTANT("role_assistant"),
    ROLE_LABORATORY( "role_laboratory");
    private final String displayValue;
    private StateRole(String displayValue){
        this.displayValue = displayValue;
    }
    public String getDisplayValue(){
        return displayValue;
    }
}
