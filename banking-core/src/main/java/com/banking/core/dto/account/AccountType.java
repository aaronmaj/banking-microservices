package com.banking.core.dto.account;

public enum AccountType {
    CHECKING(0.05, 0.0) {
        @Override
        public double calculateFees(String operation, double amount) {
            return 0;
        }

        @Override
        public double calculateInterests(double amount, int period) {
            return 0;
        }
    },
    SAVINGS(0.05, 0.0) {
        @Override
        public double calculateFees(String operation, double amount) {
            return 0;
        }

        @Override
        public double calculateInterests(double amount, int period) {
            return amount * period * getInterestRates();
        }
    },
    LOAN(0.05, 0.0) {
        @Override
        public double calculateFees(String operation, double amount) {
            return 0;
        }

        @Override
        public double calculateInterests(double amount, int period) {
            return 0;
        }
    },
    CERTIFICATE_OF_DEPOSIT(0.0,0.0){
        @Override
        public double calculateFees(String operation, double amount) {
            return 0;
        }

        @Override
        public double calculateInterests(double amount, int period) {
            return 0;
        }
    };

    private final double fees;
    private final double interestRates;

    AccountType(double fees, double interestRates) {
        this.fees = fees;
        this.interestRates = interestRates;
    }

    public double getFees() {
        return fees;
    }

    public double getInterestRates() {
        return interestRates;
    }

    public abstract double calculateFees(String operation, double amount);

    public abstract double calculateInterests(double amount, int period);
}
