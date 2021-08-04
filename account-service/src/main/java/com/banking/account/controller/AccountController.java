package com.banking.account.controller;


import com.banking.account.model.Account;
import com.banking.account.service.AccountService;
import com.banking.core.dto.account.AccountDTO;
import com.banking.core.dto.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/accounts")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;


    @GetMapping
    ResponseEntity<List<AccountDTO>> findAll() {
        LOGGER.info("Fetching all accounts....");
        List<AccountDTO> accounts = this.accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountNumber") String accountNumber) {
        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        /*accountDTO.add(
                linkTo(methodOn(AccountController.class)
                        .getAccount(accountNumber))
                        .withSelfRel(),
                linkTo(methodOn(AccountController.class)
                        .createAccount(accountDTO))
                        .withRel("createAccount"),
                linkTo(methodOn(AccountController.class)
                        .updateAccount(accountDTO))
                        .withRel("updateAccount"),
                linkTo(methodOn(AccountController.class)
                        .deleteAccount(accountNumber))
                        .withRel("deleteAccount"));

         */
        return ResponseEntity.ok(accountDTO);
    }

    @SneakyThrows
    @GetMapping(value = "/{accountNumber}/customer")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable("accountNumber")String accountNumber){
        return ResponseEntity.ok(accountService.getPersonalDetails(accountNumber));
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

    //@RequestHeader(value = "Accept-Language",required = false)Locale locale)
    @DeleteMapping(value = "/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.deleteAccount(accountNumber));
    }

}
