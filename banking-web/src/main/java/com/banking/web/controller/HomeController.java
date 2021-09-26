package com.banking.web.controller;

import com.banking.core.dto.account.AccountDto;
import com.banking.core.dto.customer.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RestTemplate restTemplate;

    @GetMapping
    public String home(Model model) {
        ParameterizedTypeReference<List<CustomerDto>> responseType =
                new ParameterizedTypeReference<List<CustomerDto>>() {};
        List<CustomerDto> customers = restTemplate.exchange("http://localhost:8082/v1/customers",
                HttpMethod.GET,
                null,
                responseType
        ).getBody();
        customers.stream().forEach(customerDto -> System.out.printf("%s %s%n",customerDto.getCustomerId(),customerDto.getCustomerName()));
        model.addAttribute("customers", customers);

        return "home";
    }
}
