package com.banking.account.controller;


import com.banking.account.service.AccountService;
import com.banking.core.dto.account.AccountDto;
import com.banking.core.dto.customer.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/accounts")
@CrossOrigin
@Tag(name = "AccountService", description = "REST API for accounts microservice.")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    /**
     * Usage: "curl $HOST:$PORT/v1/accounts".
     *
     * @return the list of accounts and their info, if found, else an empty list
     */
    @Operation(
            summary = "${api.accounts.find-all.description}",
            description = "${api.accounts.find-all.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @RolesAllowed({"ADMIN", "USER"})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    ResponseEntity<List<AccountDto>> findAll() {

        logger.info("Fetching all accounts....");
        List<AccountDto> accounts = this.accountService.findAll();
        return ResponseEntity.ok().body(accounts);
    }

    /**
     * Usage: "curl $HOST:$PORT/v1/accounts/0670234".
     *
     * @param accountNumber
     * @return the account info, if found, else null
     */
    @Operation(
            summary = "${api.accounts.get-account-by-account-number.description}",
            description = "${api.accounts.get-account-by-account-number.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountNumber") String accountNumber) {
        AccountDto accountDto = accountService.getAccountByNumber(accountNumber);
        /*accountDto.add(
                linkTo(methodOn(AccountController.class)
                        .getAccount(accountNumber))
                        .withSelfRel(),
                linkTo(methodOn(AccountController.class)
                        .createAccount(accountDto))
                        .withRel("createAccount"),
                linkTo(methodOn(AccountController.class)
                        .updateAccount(accountDto))
                        .withRel("updateAccount"),
                linkTo(methodOn(AccountController.class)
                        .deleteAccount(accountNumber))
                        .withRel("deleteAccount"));

         */
        return Optional.ofNullable(accountDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Usage: "curl $HOST:$PORT/v1/accounts/0670234/customer".
     *
     * @param accountNumber
     * @return the account info, if found, else null
     */
    @Operation(
            summary = "${api.accounts.get-customer-details.description}",
            description = "${api.accounts.get-customer-details.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @SneakyThrows
    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "/{accountNumber}/customer")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.getPersonalDetails(accountNumber));
    }

    /**
     * Sample usage, see below.
     * <p>
     * curl -X POST $HOST:$PORT/v1/accounts \
     * -H "Content-Type: application/json" --data \
     * '{"account_number":0670234,"account_name":"Aaron MAJAMBO","account_holder":"Aaron MAJAMBO", "type": "CHECKING"}'
     *
     * @param accountDto A JSON representation of the new account data transfer object
     */
    @Operation(
            summary = "${api.accounts.create-account.description}",
            description = "${api.accounts.create-account.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {

        accountService.createAccount(accountDto);
        URI uri = URI.create("/v1/accounts/" + accountDto.getAccountNumber());
        return ResponseEntity.created(uri).body(accountDto);
    }

    /**
     * Sample usage, see below.
     * <p>
     * curl -X PUT $HOST:$PORT/v1/accounts \
     * -H "Content-Type: application/json" --data \
     * '{"account_number":0670234,"account_name":"Aaron MAJAMBO","account_holder":"Aaron MAJAMBO", "type": "CHECKING"}'
     *
     * @param accountDto A JSON representation of the new account data to update
     */
    @Operation(
            summary = "${api.accounts.create-account.description}",
            description = "${api.accounts.create-account.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody AccountDto accountDto) {

        if (accountService.getAccountByNumber(accountNumber) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(accountService.updateAccount(accountDto));
    }

    /**
     * Sample usage: "curl -X DELETE $HOST:$PORT/v1/accounts/0670234".
     *
     * @param accountNumber of the account
     * @return the status of the operation (SUCCESS or FAILED)
     */
    @Operation(
            summary = "${api.accounts.delete-account.description}",
            description = "${api.accounts.delete-account.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @RolesAllowed("ADMIN")
    //@RequestHeader(value = "Accept-Language",required = false)Locale locale)
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountNumber") String accountNumber) {

        if (accountService.getAccountByNumber(accountNumber) == null)
            return ResponseEntity.notFound().build();

        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok().build();

    }

}
