package com.banking.core.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Value
@Builder(setterPrefix = "with")
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
    @JsonProperty("customer_Type")
    private CustType custType;
    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String address;
    private String district;
    private String city;
    @JsonProperty("id_number")
    private String idNumber;
    @JsonProperty("id_type")
    private IdType idType;
    private String country;
    @JsonProperty("id_delivery_date")
    private String idDeliveryDate;
    private Gender gender;
    private String occupation;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private byte[] photo;
    @JsonProperty("id_pic")
    private byte[] idPic;
    @JsonProperty("contract_pic")
    private byte[] contractPic;
    private String nationality;



}
