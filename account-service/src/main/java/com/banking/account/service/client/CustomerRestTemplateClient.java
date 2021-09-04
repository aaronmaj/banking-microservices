package com.banking.account.service.client;

import com.banking.account.filters.UserContextHolder;
import com.banking.account.mapper.CustomerMapper;
import com.banking.account.model.Customer;
import com.banking.account.repository.RedisRepository;
import com.banking.core.dto.customer.CustomerDto;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import brave.ScopedSpan;
import brave.Tracer;

@Component
public class CustomerRestTemplateClient {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRestTemplateClient.class);

    @Autowired
    Tracer tracer;

    @Autowired
    CustomerMapper mapper;

    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;
    @Autowired
    RedisRepository redisRepository;

    public CustomerDto getCustomer(String customerId) {
        logger.debug("In Account Service.getCustomer: {}", UserContextHolder.getContext().getCorrelationId());

        Customer customer = checkRedisCache(customerId);

        if (customer != null) {
            logger.debug("Successfully retrieved a customer {} from the redis cache: {}", customerId, customer);
            return mapper.convertToDto(customer);
        }

        logger.debug("Unable to locate customer from the redis cache: {}.", customerId);

        ResponseEntity<CustomerDto> restExchange =
                keycloakRestTemplate
                        .exchange("http://gateway:8072/customer/v1/customer/{customerId}",
                                HttpMethod.GET,
                                null,
                                CustomerDto.class,
                                customerId);

        CustomerDto customerDto = restExchange.getBody();
        if (customerDto != null) {
            cacheCustomerObject(mapper.convertToEntity(customerDto));
        }
        return restExchange.getBody();
    }


    private Customer checkRedisCache(String customerId) {
        ScopedSpan newSpan = tracer.startScopedSpan("readCustomerDataFromRedis");
        try {
            return redisRepository.findById(customerId).orElse(null);
        } catch (Exception ex) {
            logger.error("Error encountered while trying to retrieve customer {} check Redis Cache.  Exception {}", customerId, ex);
            return null;
        }finally {
            newSpan.tag("peer.service", "redis");
            newSpan.annotate("Client received");
            newSpan.finish();
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
