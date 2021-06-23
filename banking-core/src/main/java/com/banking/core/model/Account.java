package com.banking.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Account {
    @Id
    private Integer id;
    private String accountName;
    private LocalDate created;
}
