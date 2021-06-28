package com.banking.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class AccountHandler {

    private final AccountRepository accountRepository;

    public Mono<ServerResponse> create(ServerRequest request){
        return  ok().contentType(MediaType.APPLICATION_JSON)
        .body(accountRepository.save(request.bodyToMono())Account.class)
        ok().build();
    }

    public Mono<ServerResponse> getAccountById(ServerRequest request){
        return null;
    }
    Flux<ServerResponse> findAll(ServerRequest request){
        return  null;
    }
}
