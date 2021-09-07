package com.banking.account.model;

import com.banking.core.dto.customer.CustType;
import com.banking.core.dto.customer.Gender;
import com.banking.core.dto.customer.IdType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@RedisHash("customer")
public class Customer extends RepresentationModel<Customer> {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String customerName;
    private CustType custType;
    private LocalDate dateOfBirth;
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
    private byte[] photo;
    private byte[] idPic;
    private byte[] contractPic;
    private String nationality;
}
