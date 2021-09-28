package com.banking.transaction.repository;

import com.banking.transaction.model.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction,String> {
}
