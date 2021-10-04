package com.banking.core.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CustType {
    INDIVIDUAL("Individual"), CORPORATE("Corporate"), ASSOCIATION("Association");
    private String name;

    CustType(String name) {
        this.name = name;
    }

}
