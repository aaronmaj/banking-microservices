package com.banking.operation.service;

import org.springframework.stereotype.Service;

@Service("bankingService")
public class BankingServiceImpl implements BankingService{
    @Override
    public void depositIntoAccount(int accountId, double amount) {

    }

    @Override
    public void withdrawFromAccount(int accountId, double amount) {

    }

    @Override
    public void transferFunds(int fromAccountId, int toAccountId, double amount) {

    }
}
