package com.banking.customer.service;

import brave.ScopedSpan;
import brave.Tracer;
import com.banking.core.dto.customer.CustomerDTO;
import com.banking.customer.events.model.Action;
import com.banking.customer.events.source.SimpleSourceBean;
import com.banking.customer.model.Customer;
import com.banking.customer.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    Tracer tracer;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    @Autowired
    ModelMapper modelMapper;

    public CustomerDTO createCustomer(CustomerDTO customerDto) {
        Customer customer = customerRepository.save(convertToEntity(customerDto));
        simpleSourceBean.publishCustomerChange(Action.CREATED, customer.getCustomerId());
        return convertToDTO(customer);
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO findById(String customerId) {
        ScopedSpan newSpan = tracer.startScopedSpan("getCustDBCall");
        Optional<Customer> opt = null;
        try {
            opt = customerRepository.findById(customerId);
            simpleSourceBean.publishCustomerChange(Action.GET, customerId);

        } finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.annotate("Client received");
            newSpan.finish();
        }
        return (opt.isPresent()) ? convertToDTO(opt.get()) : null;
    }

    public CustomerDTO update(CustomerDTO customerDto) {
        Customer customer = customerRepository.save(convertToEntity(customerDto));
        simpleSourceBean.publishCustomerChange(Action.UPDATED, customer.getCustomerId());
        return convertToDTO(customer);
    }

    public CustomerDTO uploadImage(String customerId, byte[] image) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null)
            customer.setPhoto(image);
        return convertToDTO(customer);
    }

    public CustomerDTO uploadIdPic(String customerId, byte[] certPic) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null)
            customer.setIdPic(certPic);
        return convertToDTO(customer);
    }

    public CustomerDTO uploadContract(String customerId, byte[] contractPic) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null)
            customer.setContractPic(contractPic);
        return convertToDTO(customer);
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
