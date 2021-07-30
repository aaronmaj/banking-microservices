package com.banking.account.model;

import com.banking.account.model.enumeration.AccountCategory;
import com.banking.account.model.enumeration.AccountStatus;
import com.banking.account.model.enumeration.AccountType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name= "accounts")
@Entity
public class Account {
    @Id
    private Integer id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_name")
    private String accountName;
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
    @Column(name = "client_id")
    private Integer clientId;

}
