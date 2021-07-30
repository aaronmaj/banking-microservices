package com.banking.account.repository;

import com.banking.account.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {

    public Optional<Account> findByAccountNumber(String accountNumber);
}
