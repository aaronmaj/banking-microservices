package com.banking.account.service.client;

import com.banking.core.dto.customer.CustomerDto;
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

    public CustomerDto getCustomer(String customerId) {
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances("customer-service");

        if (instances.size() == 0) return null;

        String serviceUri = String.format("%s/v1/customer/%s",
                instances.get(0).getUri().toString(), customerId);

        ResponseEntity<CustomerDto> restExchange =
                restTemplate.exchange(serviceUri, HttpMethod.GET, null, CustomerDto.class, customerId);
        return restExchange.getBody();
    }
}
