package com.banking.core.dto.accounts;


import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AccountDto {

    private final Integer id;
    private final String accountNumber;
    private final String accountName;
    private final LocalDate created;
    private final AccountType accountType;
    private final AccountStatus accountStatus;
    private final AccountCategory accountCategory;
    private final double balance;
    private final CustomerDto client;

    private AccountDto(Builder builder) {
        this.id = builder.id;
        this.accountNumber = builder.accountNumber;
        this.accountName = builder.accountName;
        this.created = builder.created;
        this.accountType = builder.accountType;
        this.accountStatus = builder.accountStatus;
        this.accountCategory = builder.accountCategory;
        this.balance = builder.balance;
        this.client = builder.client;
    }


    public static class Builder {
        private Integer id;
        private final String accountNumber;
        private final CustomerDto client;
        private String accountName;
        private LocalDate created;
        private AccountType accountType;
        private AccountStatus accountStatus;
        private AccountCategory accountCategory;
        private double balance;

        public Builder(String accountNumber, CustomerDto client) {
            this.accountNumber = accountNumber;
            this.client = client;
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder withDateCreated(LocalDate created) {
            this.created = created;
            return this;
        }

        public Builder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder withAccountStatus(AccountStatus accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public Builder withAccountCategory(AccountCategory accountCategory) {
            this.accountCategory = accountCategory;
            return this;
        }

        public Builder withBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public AccountDto build() {
            return new AccountDto(this);
        }
    }

}
