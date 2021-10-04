package com.banking.core.dto.customer;


import com.fasterxml.jackson.annotation.JsonValue;

public enum IdType {
    NATIONAL_ID("National ID"),PASSPORT("Passport"),SERVICE_DOCUMENT("Service Documents");

    private String value;

    IdType(String id) {
        this.value = id;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
