package com.banking.account.mapper;

import com.banking.account.model.Customer;
import com.banking.core.dto.customer.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mappings({
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
