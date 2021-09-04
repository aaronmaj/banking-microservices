package com.banking.core.dto.transaction;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {

    private String transactionId;
    private double amount;
    private LocalDateTime date;
    private String author;
    private String authorAddress;
    private String authorPhoneNumber;
    private String authorCertNumber;
    private TransactionType type;
    private TransactionStatus status;
    private String branch;
    private String fromAccount;
    private String toAccount;
    private String description;
    private String reason;




}
