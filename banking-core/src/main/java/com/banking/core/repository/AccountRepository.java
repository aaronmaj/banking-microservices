package com.banking.core.repository;

import com.banking.core.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<Account,Integer> {
}
