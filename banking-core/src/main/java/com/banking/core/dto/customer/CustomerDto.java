package com.banking.core.dto.customer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDto {
    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String customerName;
    private final CustType custType;
    private final LocalDate dateofBirth;
    private final String address;
    private final String district;
    private final String city;
    private final String idNumber;
    private final IdType idType;
    private final String country;
    private final String idDeliveryDate;
    private final Gender gender;
    private final String occupation;
    private final String phoneNumber;
    private final String email;
    private byte[] photo;
    private byte[] idPic;
    private byte[] contractPic;
    private final String nationality;


    private CustomerDto(Builder builder) {

        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.customerName = builder.customerName;
        this.custType = builder.custType;
        this.dateofBirth = builder.dateofBirth;
        this.address = builder.address;
        this.district = builder.district;
        this.city = builder.city;
        this.idNumber = builder.idNumber;
        this.idType = builder.idType;
        this.idDeliveryDate = builder.idDeliveryDate;
        this.country = builder.country;
        this.gender = builder.gender;
        this.occupation = builder.occupation;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.photo = builder.photo;
        this.idPic = builder.idPic;
        this.contractPic = builder.contractPic;
        this.nationality = builder.nationality;
    }

    public static class Builder {
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
        private byte[] photo;
        private byte[] idPic;
        private byte[] contractPic;
        private String nationality;

        public CustomerDto build() {
            return new CustomerDto(this);
        }

        public Builder withId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder withCustType(CustType custType) {
            this.custType = custType;
            return this;
        }

        public Builder withDateOfBirth(LocalDate dateofBirth) {
            this.dateofBirth = dateofBirth;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder withIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder withIdDeliveryDate(String idDeliveryDate) {
            this.idDeliveryDate = idDeliveryDate;
            return this;
        }

        public Builder withOccupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public Builder withIdType(IdType idType) {
            this.idType = idType;
            return this;
        }

        public Builder withContractPic(byte[] contractPic) {
            this.contractPic = contractPic;
            return this;
        }

        public Builder withPhoto(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withNationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder withIdPic(byte[] idPic) {
            this.idPic = idPic;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
    }


}
