package com.banking.customer.model;

import com.banking.core.dto.customer.CustType;
import com.banking.core.dto.customer.IdType;
import com.banking.core.dto.customer.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String customerName;
    private CustType custType;
    private LocalDate dateofBirth;
    private String address;
    private String district;
    private String city;
    private String idNumber;
    private IdType idType;
    private String country;
    private String idDeliveryDate;
    private Gender gender;
    private String occupation;
    private String phoneNumber;
    private String email;
    private String photo;
    private String idPic;
    private String contractPic;
    private String nationality;
}
