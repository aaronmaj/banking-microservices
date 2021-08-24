package com.banking.operation.model;

import com.banking.core.dto.transaction.TransactionStatus;
import com.banking.core.dto.transaction.TransactionType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @Column(name="transaction_id")
    private String transactionId;
    private double amount;
    private LocalDateTime date;
    private String author;
    @Column(name="author_address")
    private String authorAddress;
    @Column(name="author_tel")
    private String authorPhoneNumber;
    @Column(name="author_cert")
    private String authorCertNumber;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String branch;
    @Column(name = "from_account")
    private String fromAccount;
    @Column(name = "to_account")
    private String toAccount;
    private String description;
    private String reason;
    private String operator;

}
