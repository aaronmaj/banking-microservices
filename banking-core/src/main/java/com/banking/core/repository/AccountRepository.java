package com.banking.core.repository;

import com.banking.core.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account,Integer> {

    Flux<Account> findAccountByName(String name);
}
