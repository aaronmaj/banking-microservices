package com.banking.customer;

import com.banking.customer.controller.CustomerController;
import com.banking.customer.repository.CustomerRepository;
import com.banking.customer.service.CustomerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureRestDocs
public class CustomerControllerTest {

    private WebTestClient webTestClient;

    @MockBean
    CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(@Autowired MockMvc mockMvc, @Autowired RestDocumentationContextProvider restDocumentation){
        this.webTestClient = MockMvcWebTestClient
                .bindTo(mockMvc)
                .filter(documentationConfiguration(restDocumentation))
                .build();

    }
}
