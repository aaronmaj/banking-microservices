package com.banking.account.service.client;

import com.banking.core.dto.customer.CustomerDTO;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

@Component
public class CustomerRestTemplateClient {

    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;

    public CustomerDTO getCustomer(String customerId) {

        return keycloakRestTemplate
                .exchange("http://gateway:8072/customer/v1/customer/{customerId}",
                        HttpMethod.GET,
                        null,
                        CustomerDTO.class,
                        customerId)
                .getBody();
    }
}
