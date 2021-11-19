package com.banking.customer.service;

import brave.ScopedSpan;
import brave.Tracer;
import com.banking.core.dto.customer.CustomerDto;
import com.banking.customer.events.model.Action;
import com.banking.customer.events.source.SimpleSourceBean;
import com.banking.customer.mappers.CustomerMapper;
import com.banking.customer.model.Customer;
import com.banking.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    Tracer tracer;
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    //@Autowired
    //SimpleSourceBean simpleSourceBean;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer=mapper.convertToEntity(customerDto);
        customer.setNewEntity(true);
        customer = customerRepository.save(customer);
        //simpleSourceBean.publishCustomerChange(Action.CREATED, customer.getCustomerId());
        return mapper.convertToDto(customer);
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public CustomerDto findById(String customerId) {
        ScopedSpan newSpan = tracer.startScopedSpan("getCustDBCall");
        Optional<Customer> opt = null;
        try {
            opt = customerRepository.findById(customerId);
            //simpleSourceBean.publishCustomerChange(Action.GET, customerId);

        } finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.annotate("Client received");
            newSpan.finish();
        }
        return (opt.isPresent()) ? mapper.convertToDto(opt.get()) : null;
    }

    public CustomerDto update(String customerId, CustomerDto customerDto) {
        Customer body = mapper.convertToEntity(customerDto);
        Customer existing = customerRepository.findById(customerId).get();
        existing.setFirstName(body.getFirstName());
        existing.setLastName(body.getLastName());
        existing.setCustomerName(body.getCustomerName());
        existing.setAddress(body.getAddress());
        existing.setCity(body.getCity());
        existing.setCountry(body.getCountry());
        existing.setCustType(body.getCustType());
        existing.setDistrict(body.getDistrict());
        existing.setDateOfBirth(body.getDateOfBirth());
        existing.setEmail(body.getEmail());
        existing.setGender(body.getGender());
        existing.setId(body.getId());
        existing.setIdDeliveryDate(body.getIdDeliveryDate());
        existing.setPhoneNumber(body.getPhoneNumber());
        existing.setOccupation(body.getOccupation());
        existing.setNationality(body.getNationality());

        Customer updatedCustomer = customerRepository.save(existing);
        logger.info("Customer information updated successfully......");
       // simpleSourceBean.publishCustomerChange(Action.UPDATED, customer.getCustomerId());
        return mapper.convertToDto(updatedCustomer);
    }

    public CustomerDto uploadImage(String customerId, byte[] image) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null)
            customer.setPhoto(image);
        return mapper.convertToDto(customer);
    }

    public CustomerDto uploadIdPic(String customerId, byte[] certPic) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null)
            customer.setIdPic(certPic);
        return mapper.convertToDto(customer);
    }

    public CustomerDto uploadContract(String customerId, byte[] contractPic) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null)
            customer.setContractPic(contractPic);
        return mapper.convertToDto(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
        //simpleSourceBean.publishCustomerChange(Action.DELETED, customerId);
    }


}
