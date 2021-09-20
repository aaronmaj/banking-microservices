package com.banking.transaction.model;

import com.banking.core.dto.transaction.Direction;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import com.banking.core.dto.common.Amount;
import com.banking.core.dto.transaction.TransactionStatus;
import com.banking.core.dto.transaction.TransactionType;

import java.time.LocalDate;

public class Transaction {
    @Id
    private String transactionId;
    @Version
    private Integer version;
    private LocalDate date;
    private TransactionStatus status;
    private TransactionType type;
    private Amount amount;
    private String description;
    private Direction direction;
}
