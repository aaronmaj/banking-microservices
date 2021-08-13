package com.banking.account.service.client;

import com.banking.account.filters.UserContextHolder;
import com.banking.account.model.Customer;
import com.banking.account.repository.RedisRepository;
import com.banking.core.dto.customer.CustomerDTO;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestTemplateClient {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRestTemplateClient.class);
    @Autowired
    ModelMapper mapper;

    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;
    @Autowired
    RedisRepository redisRepository;

    public CustomerDTO getCustomer(String customerId) {
        logger.debug("In Account Service.getCustomer: {}", UserContextHolder.getContext().getCorrelationId());

        Customer customer = checkRedisCache(customerId);

        if (customer != null) {
            logger.debug("Successfully retrieved a customer {} from the redis cache: {}", customerId, customer);
            return mapper.map(customer, CustomerDTO.class);
        }

        logger.debug("Unable to locate customer from the redis cache: {}.", customerId);

        ResponseEntity<CustomerDTO> restExchange =
                keycloakRestTemplate
                        .exchange("http://gateway:8072/customer/v1/customer/{customerId}",
                                HttpMethod.GET,
                                null,
                                CustomerDTO.class,
                                customerId);

        CustomerDTO customerDTO = restExchange.getBody();
        if (customerDTO != null) {
            cacheCustomerObject(mapper.map(customerDTO, Customer.class));
        }
        return restExchange.getBody();
    }


    private Customer checkRedisCache(String customerId) {
        try {
            return redisRepository.findById(customerId).orElse(null);
        } catch (Exception ex) {
            logger.error("Error encountered while trying to retrieve customer {} check Redis Cache.  Exception {}", customerId, ex);
            return null;
        }
    }

    private void cacheCustomerObject(Customer customer) {
        try {
            redisRepository.save(customer);
        } catch (Exception ex) {
            logger.error("Unable to cache customer {} in Redis. Exception {}", customer.getCustomerId(), ex);
        }
    }
}
