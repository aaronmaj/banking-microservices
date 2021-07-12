package com.banking.accounts.controller;

import com.banking.accounts.model.Account;
import com.banking.accounts.service.AccountService;
import com.banking.core.dto.accounts.AccountDTO;
import com.banking.core.dto.transactions.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    @GetMapping
    ResponseEntity<List<AccountDTO>> findAll() {

        List<AccountDTO> accounts = this.accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountId") Integer accountId) {
        AccountDTO accountDTO = accountService.findById(accountId);
        accountDTO.add(
                linkTo(methodOn(AccountController.class)
                        .getAccount(accountId))
                        .withSelfRel(),
                linkTo(methodOn(AccountController.class)
                        .createAccount(accountDTO))
                        .withRel("createAccount"),
                linkTo(methodOn(AccountController.class)
                        .updateAccount(accountDTO))
                        .withRel("updateAccount"),
                linkTo(methodOn(AccountController.class)
                        .deleteAccount(accountId))
                        .withRel("deleteAccount"));
        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.convertToEntity(accountDTO);
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.convertToEntity(accountDTO);
        return ResponseEntity.ok(accountService.updateAccount(account));
    }

    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountId") Integer accountId) {
        return ResponseEntity.ok(accountService.deleteAccount(accountId));
    }

}
