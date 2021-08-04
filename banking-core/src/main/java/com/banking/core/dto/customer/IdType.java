package com.banking.core.dto.account;


public enum IdType {
    NATIONAL_ID(1),PASSPORT(2),SERVICE_DOCUMENT(3);

    private Integer id;

    IdType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
