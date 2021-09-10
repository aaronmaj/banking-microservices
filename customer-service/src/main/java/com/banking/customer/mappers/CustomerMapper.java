package com.banking.customer.mappers;

import com.banking.core.dto.customer.CustomerDto;
import com.banking.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {
    /*@Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target ="contractPic", ignore = true )
    })*/
    CustomerDto convertToDto(Customer customer);
    /*
    @Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target = "contractPic", ignore = true)
    })*/
    Customer convertToEntity(CustomerDto customerDto);
}
