package com.banking.services.api.config;

import com.banque.services.core.service.CorebankingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CorebankingService corebankingService()
    {
       CorebankingService corebankingService = new CorebankingService();
       return  corebankingService;
    }
}
