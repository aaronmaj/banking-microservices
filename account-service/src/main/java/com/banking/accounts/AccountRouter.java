package com.banking.accounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class AccountRouter {

    @Bean
    RouterFunction<ServerResponse> routes(AccountService accountService){
        return null;
    }
    @Bean
    RouterFunction<ServerResponse> route(AccountHandler accountHandler){
        return null;
    }
}
