package com.banking.core.dto.account;


import com.banking.core.dto.common.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

//import org.springframework.hateoas.RepresentationModel;
@Getter
@Value
@Builder(builderClassName = "Builder", setterPrefix = "with")
@Jacksonized
public class AccountDto {
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("account_holder")
    private String accountHolder;
    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate created;
    @JsonProperty("type")
    private AccountType accountType;
    @JsonProperty("status")
    private AccountStatus accountStatus;
    @JsonProperty("category")
    private AccountCategory accountCategory;
    private double balance;
    @JsonProperty("customer_id")
    private String customerId;
    private Currency currency;
    private String branch;

}
