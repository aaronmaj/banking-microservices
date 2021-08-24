package com.banking.core.dto.transaction;


import com.banking.core.dto.account.AccountDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {

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
