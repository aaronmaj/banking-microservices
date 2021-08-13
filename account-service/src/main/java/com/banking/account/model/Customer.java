package com.banking.account.model;

import com.banking.core.dto.customer.CustType;
import com.banking.core.dto.customer.Gender;
import com.banking.core.dto.customer.IdType;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Id;
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
