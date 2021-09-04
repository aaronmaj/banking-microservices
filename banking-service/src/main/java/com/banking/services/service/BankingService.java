package com.banking.operation.service;

public interface BankingService {
    void depositIntoAccount(int accountId, double amount);
    void withdrawFromAccount(int accountId, double amount);
    void transferFunds(int fromAccountId, int toAccountId, double amount);
}
