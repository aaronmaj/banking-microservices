package com.banking.account.service.client;

import com.banking.core.dto.accounts.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("customer-service")
public interface CustomerFeignClient {

    @RequestMapping(method= RequestMethod.GET,value="/v1/customer/{customerId}",consumes="application/json")
    CustomerDTO getCustomer(@PathVariable("customerId") String customerId);
}
