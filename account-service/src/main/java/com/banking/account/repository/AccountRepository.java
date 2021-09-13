package com.banking.account.repository;

import com.banking.account.model.Account;
import com.banking.account.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {

    public Optional<Account> findByAccountNumber(String accountNumber);

    @Transactional(readOnly = true)
    public Optional<Customer> findByCustomerId(String customerId);
}
