package com.banking.account.controller;


import com.banking.account.service.AccountService;
import com.banking.core.dto.account.AccountDto;
import com.banking.core.dto.customer.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/account")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping
    ResponseEntity<List<AccountDto>> findAll() {
        LOGGER.info("Fetching all accounts....");
        List<AccountDto> accounts = this.accountService.findAll();
        return ResponseEntity.ok(accounts);
    }
    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountNumber") String accountNumber) {
        AccountDto accountDTO = accountService.getAccountByNumber(accountNumber);
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
    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping(value = "/{accountNumber}/customer")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable("accountNumber")String accountNumber){
        return ResponseEntity.ok(accountService.getPersonalDetails(accountNumber));
    }

    @PostMapping
    @RolesAllowed({ "ADMIN", "USER" })
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDTO) {

        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }
    @PutMapping
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDTO) {

        return ResponseEntity.ok(accountService.updateAccount(accountDTO));
    }

    @RolesAllowed("ADMIN")
    //@RequestHeader(value = "Accept-Language",required = false)Locale locale)
    @DeleteMapping(value = "/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.deleteAccount(accountNumber));
    }

}
