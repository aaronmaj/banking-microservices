package com.banking.customer.model;

import com.banking.core.dto.customer.CustType;
import com.banking.core.dto.customer.IdType;
import com.banking.core.dto.customer.Gender;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name="customer_id")
    private String customerId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name="customer_type")
    private CustType custType;
    @Column(name= "date_of_birth")
    private LocalDate dateOfBirth;
    private String address;
    private String district;
    private String city;
    @Column(name= "id_number")
    private String idNumber;
    @Column(name="id_type")
    private IdType idType;
    private String country;
    @Column(name="id_delivery_date")
    private String idDeliveryDate;
    private Gender gender;
    private String occupation;
    @Column(name="phone_number")
    private String phoneNumber;
    private String email;
    private byte[] photo;
    @Column(name="id_pic")
    private byte[] idPic;
    @Column(name="contract_pic")
    private byte[] contractPic;
    private String nationality;
}
