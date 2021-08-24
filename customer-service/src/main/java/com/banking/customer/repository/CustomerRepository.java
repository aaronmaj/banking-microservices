package com.banking.customer.repository;

import com.banking.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    public List<Customer> findAll();
}
