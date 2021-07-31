package com.banking.account.service.client;

import com.banking.core.dto.accounts.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

@Component
public class CustomerRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public CustomerDTO getCustomer(String customerId) {

        return restTemplate
                .exchange("http://customer-service/v1/{customerId}",
                        HttpMethod.GET,
                        null,
                        CustomerDTO.class,
                        customerId)
                .getBody();
    }
}
