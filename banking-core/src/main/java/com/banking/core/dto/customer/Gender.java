package com.banking.core.dto.customer;


import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("Male"), FEMALE("Female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
