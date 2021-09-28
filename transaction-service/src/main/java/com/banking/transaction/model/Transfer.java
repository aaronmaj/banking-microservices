package com.banking.transaction.model;

import com.banking.core.dto.account.AccountType;
import com.banking.core.dto.common.Currency;
import com.banking.core.dto.transaction.Direction;
import com.banking.core.dto.transaction.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Getter
@Setter
public class Transfer {

    @Id
    private String id;
    @Column(value = "account_number")
    private String accountNumber;
    private Integer amount;
    private String batchId;
    private Currency currency = Currency.BIF;
    private Integer counterpartyId;
    private Direction direction = Direction.CREDIT;
    private LocalDate effectiveDate;
    private String memo;
    private Object metadata;
    private String receiverAccountNumber;
    private AccountType receiverAccountType = AccountType.CHECKING;
    private String receiverName;
    private String receiverRoutingNumber;
    private String returnValue;
    private TransactionStatus status ;
    private String type;
    private String url;

}
