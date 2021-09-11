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
            @Mapping(target = "withPhoto", ignore = true),
            @Mapping(target = "withIdPic", ignore = true),
            @Mapping(target ="withContractPic", ignore = true )
    })
    CustomerDto convertToDto(Customer customer);

    @Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "idPic", ignore = true),
            @Mapping(target = "contractPic", ignore = true)
    })
    Customer convertToEntity(CustomerDto customerDto);
}
