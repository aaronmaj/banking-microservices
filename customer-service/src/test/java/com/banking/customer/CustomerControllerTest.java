package com.banking.customer;

import com.banking.core.dto.customer.CustType;
import com.banking.core.dto.customer.CustomerDto;
import com.banking.core.dto.customer.Gender;
import com.banking.core.dto.customer.IdType;
import com.banking.customer.config.SecurityConfig;
import com.banking.customer.controller.CustomerController;
import com.banking.customer.mappers.CustomerMapper;
import com.banking.customer.repository.CustomerRepository;
import com.banking.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureRestDocs
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(@Autowired MockMvc mockMvc, @Autowired RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = MockMvcWebTestClient
                .bindTo(mockMvc)
                .filter(documentationConfiguration(restDocumentation))
                .build();

    }

    @Test
    void findAllCustomers() {
        when(customerService.findAll()).thenReturn(
                Arrays.asList(CustomerDto.builder()
                                .customerId("001670")
                                .firstName("Aaron")
                                .lastName("MAJAMBO")
                                .customerName("Aaron MAJAMBO")
                                .address("8,Avenue Musasa")
                                .city("BUJUMBURA")
                                .custType(CustType.INDIVIDUAL)
                                .country("Burundi")
                                .district("BUJUMBURA MAIRIE")
                                .email("aaronmajb@gmail.com")
                                .gender(Gender.MALE)
                                .dateOfBirth(LocalDate.of(1983, 3, 13))
                                .idNumber("0201/302.020.001")
                                .idType(IdType.NATIONAL_ID)
                                .build()
                        )
                );
        this.webTestClient.get().uri("/v1/customers")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findAll", preprocessResponse(prettyPrint())));
    }

    @Test
    void postNewCustomer() {
        when(customerService.createCustomer(any()))
                .thenReturn(CustomerDto.builder()
                        .customerId("001670")
                        .firstName("Aaron")
                        .lastName("MAJAMBO")
                        .customerName("Aaron MAJAMBO")
                        .address("8,Avenue Musasa")
                        .city("BUJUMBURA")
                        .custType(CustType.INDIVIDUAL)
                        .country("Burundi")
                        .district("BUJUMBURA MAIRIE")
                        .email("aaronmajb@gmail.com")
                        .gender(Gender.MALE)
                        .dateOfBirth(LocalDate.of(1983, 3, 13))
                        .idNumber("0201/302.020.001")
                        .idType(IdType.NATIONAL_ID)
                        .build()
                );

        webTestClient
                .post()
                .uri("/v1/customers")
                .bodyValue(CustomerDto.builder()
                        .customerId("001670")
                        .firstName("Aaron")
                        .lastName("MAJAMBO")
                        .customerName("Aaron MAJAMBO")
                        .address("8,Avenue Musasa")
                        .city("BUJUMBURA")
                        .custType(CustType.INDIVIDUAL)
                        .country("Burundi")
                        .district("BUJUMBURA MAIRIE")
                        .email("aaronmajb@gmail.com")
                        .gender(Gender.MALE)
                        .dateOfBirth(LocalDate.of(1983, 3, 13))
                        .idNumber("0201/302.020.001")
                        .idType(IdType.NATIONAL_ID)
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerDto.class)
                .consumeWith(document("post-new-customer", preprocessResponse(prettyPrint())));


    }

    @Test
    void updateCustomer() {

        when(customerService.findById(anyString()))
                .thenReturn(CustomerDto.builder()
                        .customerId("001670")
                        .firstName("Aaron")
                        .lastName("MAJAMBO")
                        .customerName("Aaron MAJAMBO")
                        .address("8,Avenue Musasa")
                        .city("BUJUMBURA")
                        .custType(CustType.INDIVIDUAL)
                        .country("Burundi")
                        .district("BUJUMBURA MAIRIE")
                        .email("aaronmajb@gmail.com")
                        .gender(Gender.MALE)
                        .dateOfBirth(LocalDate.of(1983, 3, 13))
                        .idNumber("0201/302.020.001")
                        .idType(IdType.NATIONAL_ID)
                        .build()
                );

        when(customerService.update(anyString(), any(CustomerDto.class)))
                .thenReturn(CustomerDto.builder()
                .customerId("001670")
                .firstName("Aaron")
                .lastName("MAJAMBO")
                .customerName("Aaron MAJAMBO")
                .address("8,Avenue Musasa")
                .city("BUJUMBURA")
                .custType(CustType.INDIVIDUAL)
                .country("Burundi")
                .district("BUJUMBURA MAIRIE")
                .email("aaronmajb@gmail.com")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1983, 3, 13))
                .idNumber("0201/302.020.001")
                .idType(IdType.NATIONAL_ID)
                .build()
        );

        webTestClient
                .put()
                .uri("/v1/customers/001670")
                .bodyValue(CustomerDto.builder()
                        .customerId("001670")
                        .firstName("Aaron")
                        .lastName("MAJAMBO")
                        .customerName("Aaron MAJAMBO")
                        .address("8,Avenue Musasa")
                        .city("BUJUMBURA")
                        .custType(CustType.INDIVIDUAL)
                        .country("Burundi")
                        .district("BUJUMBURA MAIRIE")
                        .email("aaronmajb@gmail.com")
                        .gender(Gender.MALE)
                        .dateOfBirth(LocalDate.of(1983, 3, 13))
                        .idNumber("0201/302.020.001")
                        .idType(IdType.NATIONAL_ID)
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerDto.class)
                .consumeWith(document("update-customer", preprocessResponse(prettyPrint())));


    }


    @Test
    void findOneCustomer() {
        when(customerService.findById("001670")).thenReturn(
                CustomerDto.builder()
                        .customerId("001670")
                        .firstName("Aaron")
                        .lastName("MAJAMBO")
                        .customerName("Aaron MAJAMBO")
                        .address("8,Avenue Musasa")
                        .city("BUJUMBURA")
                        .custType(CustType.INDIVIDUAL)
                        .country("Burundi")
                        .district("BUJUMBURA MAIRIE")
                        .email("aaronmajb@gmail.com")
                        .gender(Gender.MALE)
                        .dateOfBirth(LocalDate.of(1983, 3, 13))
                        .idNumber("0201/302.020.001")
                        .idType(IdType.NATIONAL_ID)
                        .build());
        webTestClient.get().uri("/v1/customers/001670")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerDto.class)
                .consumeWith(document("findById", preprocessResponse(prettyPrint())));
    }
}
