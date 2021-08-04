package com.banking.account.service.client;

import com.banking.core.dto.customer.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CustomerDiscoveryClient {

    @Autowired
    DiscoveryClient discoveryClient;

    public CustomerDTO getCustomer(String customerId) {
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances("customer-service");

        if (instances.size() == 0) return null;

        String serviceUri = String.format("%s/v1/customer/%s",
                instances.get(0).getUri().toString(), customerId);

        ResponseEntity<CustomerDTO> restExchange =
                restTemplate.exchange(serviceUri, HttpMethod.GET, null, CustomerDTO.class, customerId);
        return restExchange.getBody();
    }
}
