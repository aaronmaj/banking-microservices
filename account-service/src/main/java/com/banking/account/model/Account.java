package com.banking.account.model;

import com.banking.core.dto.account.AccountCategory;
import com.banking.core.dto.account.AccountStatus;
import com.banking.core.dto.account.AccountType;
import com.banking.core.dto.account.Currency;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "accounts")
@Entity
public class Account {
    @Id
    private Integer id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "customer_id")
    private String customerId;
    private String owner;
    private LocalDate created;
    @Column(name = "account_type")
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;
    @Column(name = "account_status")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus;
    @Column(name = "account_category")
    @Enumerated(EnumType.ORDINAL)
    private AccountCategory accountCategory;
    private double balance;
    @Column(name = "available_balance")
    private double availableBalance;
    @Column(name = "current_balance")
    private double currentBalance;
    private Integer numberOfSignatories;
    @Enumerated
    private Currency currency;
    @ManyToOne
    private Branch branch;



}
