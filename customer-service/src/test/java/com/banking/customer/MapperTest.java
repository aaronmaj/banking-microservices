package com.banking.customer;


import com.banking.core.dto.customer.CustType;
import com.banking.core.dto.customer.CustomerDto;
import com.banking.core.dto.customer.Gender;
import com.banking.core.dto.customer.IdType;
import com.banking.customer.mappers.CustomerMapper;
import com.banking.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapperTest {
    private CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void mapperTests(){
        assertNotNull(mapper);

        CustomerDto dto = CustomerDto.builder()
                .customerId("001671")
                .firstName("Adele")
                .lastName("SEMONDO")
                .customerName("Adele SEMONDO")
                .address("8,Avenue Hipopotames, KININDO")
                .city("BUJUMBURA")
                .custType(CustType.INDIVIDUAL)
                .country("Burundi")
                .district("BUJUMBURA MAIRIE")
                .email("adele.semondo@gmail.com")
                .gender(Gender.FEMALE)
                .dateOfBirth(LocalDate.of(1978,6,17))
                .idNumber("0201/152.0201.561")
                .idType(IdType.NATIONAL_ID)
                .build();

        Customer entity = mapper.convertToEntity(dto);
        System.out.printf("%s", entity.getId());

        assertEquals(dto.getCustomerId(), entity.getId());
        assertEquals(dto.getCustomerName(), entity.getCustomerName());
        assertEquals(dto.getAddress(), entity.getAddress());
        assertEquals(dto.getCity(), entity.getCity());
        assertEquals(dto.getCustType(), entity.getCustType());
        assertEquals(dto.getIdNumber(), entity.getIdNumber());
        assertEquals(dto.getCountry(), entity.getCountry());
        assertEquals(dto.getDateOfBirth(), entity.getDateOfBirth());


        CustomerDto dto1 = mapper.convertToDto(entity);

        assertEquals(dto.getCustomerId(), dto1.getCustomerId());
        assertEquals(dto.getCustomerName(), dto1.getCustomerName());
        assertEquals(dto.getAddress(), dto1.getAddress());
        assertEquals(dto.getCity(), dto1.getCity());
        assertEquals(dto.getCustType(), dto1.getCustType());
        assertEquals(dto.getIdNumber(), dto1.getIdNumber());
        assertEquals(dto.getCountry(), dto1.getCountry());
        assertEquals(dto.getDateOfBirth(), dto1.getDateOfBirth());


    }
}
