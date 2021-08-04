package com.banking.core.dto.accounts;


import lombok.Getter;

import java.time.LocalDate;
//import org.springframework.hateoas.RepresentationModel;
@Getter
public class AccountDTO {

    private final String accountNumber;
    private final String accountName;
    private final LocalDate created;
    private final AccountType accountType;
    private final AccountStatus accountStatus;
    private final AccountCategory accountCategory;
    private final double balance;
    private final String customerId;
    private final Currency currency;

    private AccountDTO(Builder builder) {

        this.accountNumber = builder.accountNumber;
        this.accountName = builder.accountName;
        this.created = builder.created;
        this.accountType = builder.accountType;
        this.accountStatus = builder.accountStatus;
        this.accountCategory = builder.accountCategory;
        this.balance = builder.balance;
        this.customerId = builder.customerId;
        this.currency = builder.currency;
    }


    public static class Builder {

        private String accountNumber;
        private String customerId;
        private String accountName;
        private LocalDate created;
        private AccountType accountType;
        private AccountStatus accountStatus;
        private AccountCategory accountCategory;
        private double balance;
        private Currency currency;

        public Builder() {

        }

        public Builder(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Builder withClientId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
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
        public Builder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }
        public AccountDTO build() {
            return new AccountDTO(this);
        }


    }

}
