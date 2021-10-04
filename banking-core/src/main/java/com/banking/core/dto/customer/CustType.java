package com.banking.core.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustType {
    INDIVIDUAL("Individual"),
    CORPORATE("Corporate"),
    ASSOCIATION("Association");

    private String value;

    CustType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
