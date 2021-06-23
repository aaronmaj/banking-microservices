package com.banking.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDto {
    private String accountName;
    private LocalDate created;
}
