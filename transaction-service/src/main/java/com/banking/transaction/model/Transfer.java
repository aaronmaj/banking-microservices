package com.banking.core.dto.transaction;

import com.banking.core.dto.account.AccountType;
import com.banking.core.dto.common.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransferDto {

    /**
     * Uniquely identifies each bank transfer object.
     */
    private String id;
    private String accountNumber;
    private Integer amount;
    private String batchId;
    @Builder.Default
    private Currency currency = Currency.BIF;
    private Integer counterpartyId;
    @Builder.Default
    private Direction direction = Direction.CREDIT;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;
    private String memo;
    private Object metadata;
    private String receiverAccountNumber;
    @Builder.Default
    private AccountType receiverAccountType = AccountType.CHECKING;
    private String receiverName;
    private String receiverRoutingNumber;
    @JsonProperty("return") //return is Java key word
    private String returnValue;
    private TransactionStatus status ;
    private String type;
    private String url;

}
