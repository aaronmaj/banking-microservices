package com.banking.transaction.service;

import com.banking.transaction.model.Transaction;
import com.banking.transaction.repository.TransactionRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction transfer(String source, String destination, Double amount){
        ((TransactionService) (AopContext.currentProxy())).checkBalance(source);
        return  null;
    }
    public double checkBalance(String accountNumber) {
        return 0;
    }
}
