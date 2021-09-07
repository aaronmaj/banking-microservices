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
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    @Column(name = "account_name", nullable = false)
    private String accountName;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    private String owner;
    @Column(name="date_created", nullable = false)
    private LocalDate created;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Column(name = "account_category")
    @Enumerated(EnumType.STRING)
    private AccountCategory accountCategory;
    private double balance;
    @Column(name = "available_balance")
    private double availableBalance;
    @Column(name = "current_balance")
    private double currentBalance;
    @Column(name = "signatories")
    private Integer numberOfSignatories;
    @Enumerated
    private Currency currency;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="branch_id", nullable = false)
    private Branch branch;



}
