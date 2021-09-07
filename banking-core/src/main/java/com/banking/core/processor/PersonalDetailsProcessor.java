package com.banking.core.processor;

import com.banking.core.dto.customer.CustomerDto;

public class PersonalDetailsProcessor {

    private final CustomerDto customerDto;

    public PersonalDetailsProcessor() {
        customerDto = CustomerDto.builder()
                .build();
    }
}
