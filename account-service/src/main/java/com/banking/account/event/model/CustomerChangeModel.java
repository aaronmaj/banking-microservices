package com.banking.account.event.model;

import lombok.Data;

@Data
public class CustomerChangeModel {
    private String type;
    private Action action;
    private String customerId;
    private String correlationId;

    public CustomerChangeModel(String type, Action action, String customerId, String correlationId) {
        super();
        this.type = type;
        this.action = action;
        this.customerId = customerId;
        this.correlationId = correlationId;
    }
}
