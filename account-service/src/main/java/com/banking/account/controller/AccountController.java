package com.banking.account.controller;


import com.banking.account.model.Account;
import com.banking.account.service.AccountService;
import com.banking.core.dto.accounts.AccountDTO;
import com.banking.core.dto.accounts.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;


    @GetMapping
    ResponseEntity<List<AccountDTO>> findAll() {

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
    @GetMapping(value = "/{accountNumber}/customer")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("accountNumber")String acvountNumber){
        return ResponseEntity.ok(accountService.getCustomerByAccount(acvountNumber));
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
