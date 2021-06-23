package com.banking.core.service;

import com.banking.core.model.Account;
import com.banking.core.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    Mono<ServerResponse> getAccountByName(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(accountRepository.findAccountByName(serverRequest.pathVariable("name")), Account.class);
    }

    Mono<ServerResponse> getAccounts(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(accountRepository.findAll(),Account.class);
    }
}
