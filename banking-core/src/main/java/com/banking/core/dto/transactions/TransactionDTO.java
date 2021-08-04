package com.banking.core.dto.transactions;


import com.banking.core.dto.account.AccountDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {

    private Integer id;
    private  String  txnId;
    private  String description;
    private  double amount;
    private  LocalDate date;
    private AccountDTO source;
    private AccountDTO destination;




}
