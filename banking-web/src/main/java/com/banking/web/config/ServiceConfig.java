package com.banking.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Data
@Configuration
@ConfigurationProperties(prefix = "account.service")
public class ServiceConfig {

    private String server;
    private String username;
    private String password;


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
