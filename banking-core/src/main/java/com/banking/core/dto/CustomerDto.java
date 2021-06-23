package com.banking.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {
    private Integer id;
    private String name;
    private LocalDate dateofBirth;
    private String address;
    private String district;
    private String city;
    private String idNumber;
    private String country;
}
