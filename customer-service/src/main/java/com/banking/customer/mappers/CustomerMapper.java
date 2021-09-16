package com.banking.customer.mappers;

import com.banking.core.dto.customer.CustomerDto;
import com.banking.customer.model.Customer;
import org.mapstruct.*;

//@MapperConfig(builder = @Builder(disableBuilder = true))
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {
    @Mappings({
          /*  @Mapping(source = "customerId", target = "withCustomerId"),
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
*/
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target ="contractPic", ignore = true ),
            @Mapping(source = "id", target = "customerId")
    })
    CustomerDto convertToDto(Customer customer);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "newEntity", ignore = true),
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target = "contractPic", ignore = true),
            @Mapping(source = "customerId", target = "id")
    })
    Customer convertToEntity(CustomerDto customerDto);
}
