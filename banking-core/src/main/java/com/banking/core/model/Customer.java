package com.banking.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Customer {
    @Id
    private Integer id;
    private String name;
    private LocalDate dateofBirth;
    private String address;
    private String district;
    private String city;
    private String idNumber;
    private String country;
}
