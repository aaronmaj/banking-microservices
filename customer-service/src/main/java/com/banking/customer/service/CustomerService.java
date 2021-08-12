package com.banking.customer.service;

import com.banking.core.dto.customer.CustomerDTO;
import com.banking.customer.events.source.SimpleSourceBean;
import com.banking.customer.events.model.Action;
import com.banking.customer.model.Customer;
import com.banking.customer.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    @Autowired
    ModelMapper modelMapper;

    public CustomerDTO createCustomer(Customer customer) {
        customer = customerRepository.save(customer);
        simpleSourceBean.publishCustomerChange(Action.CREATED, customer.getCustomerId());
        return convertToDTO(customer);
    }

    public CustomerDTO findById(String customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        simpleSourceBean.publishCustomerChange(Action.GET, customerId);
        return (opt.isPresent()) ? convertToDTO(opt.get()) : null;
    }

    public void update(Customer customer) {
        customerRepository.save(customer);
        simpleSourceBean.publishCustomerChange(Action.UPDATED, customer.getCustomerId());
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
        simpleSourceBean.publishCustomerChange(Action.DELETED, customerId);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customer;
    }
}
