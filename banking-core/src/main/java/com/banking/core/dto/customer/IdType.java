package com.banking.core.dto.customer;


public enum IdType {
    NATIONAL_ID("National ID"),PASSPORT("Passport"),SERVICE_DOCUMENT("Service Documents");

    private String type;

    IdType(String id) {
        this.type = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
