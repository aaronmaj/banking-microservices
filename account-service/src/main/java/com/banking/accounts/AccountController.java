package com.banking.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequiredArgsConstructor
@RequestMapping( path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final MediaType mediaType =MediaType.APPLICATION_JSON;

    @DeleteMapping
    Flux<Account> deleteAll(){
        return this.accountRepository.findAll();
    }

}
