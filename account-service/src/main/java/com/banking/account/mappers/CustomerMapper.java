package com.banking.account.mappers;

import com.banking.account.model.Customer;
import com.banking.core.dto.customer.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mappings({
            /*@Mapping(source = "customerId", target = "withCustomerId"),
            @Mapping(source = "firstName", target = "withFirstName"),
            @Mapping(source = "lastName", target = "withLastName"),
            @Mapping(source = "customerName", target = "withCustomerName"),
            @Mapping(source = "custType", target = "withCustType"),
            @Mapping(source = "dateOfBirth", target = "withDateOfBirth"),
            @Mapping(source = "address", target = "withAddress"),
            @Mapping(source = "district", target = "withDistrict"),
            @Mapping(source = "city", target = "withCity"),
            @Mapping(source = "idNumber", target = "withIdNumber"),
            @Mapping(source = "idType", target = "withIdType"),
            @Mapping(source = "country", target = "withCountry"),
            @Mapping(source = "idDeliveryDate", target = "withIdDeliveryDate"),
            @Mapping(source = "gender", target = "withGender"),
            @Mapping(source = "occupation", target = "withOccupation"),
            @Mapping(source = "phoneNumber", target = "withPhoneNumber"),
            @Mapping(source = "email", target = "withEmail"),
            @Mapping(source = "nationality", target = "withNationality"),

            @Mapping(target = "withPhoto", ignore = true),
            @Mapping(target = "withIdPic", ignore = true),
            @Mapping(target ="withContractPic", ignore = true )

             */
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target ="contractPic", ignore = true )
    })


    CustomerDto convertToDto(Customer customer);

    @Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target = "contractPic", ignore = true)
    })


    Customer convertToEntity(CustomerDto customerDto);
}
