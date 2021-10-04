package com.banking.core.dto.customer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder(builderClassName = "Builder", setterPrefix = "")
@Jacksonized
public class CustomerDto {
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("cust_type")
    private CustType custType;
    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @JsonProperty
    private String address;
    @JsonProperty
    private String district;
    @JsonProperty
    private String city;
    @JsonProperty("id_number")
    private String idNumber;
    @JsonProperty("id_type")
    private IdType idType;
    @JsonProperty
    private String country;
    @JsonProperty("id_delivery_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String idDeliveryDate;
    @JsonProperty
    private Gender gender;
    @JsonProperty
    private String occupation;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty
    private String email;
    @JsonProperty
    private byte[] photo;
    @JsonProperty("id_pic")
    private byte[] idPic;
    @JsonProperty("contract_pic")
    private byte[] contractPic;
    @JsonProperty
    private String nationality;



}
