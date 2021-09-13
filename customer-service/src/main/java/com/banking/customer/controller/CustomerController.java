package com.banking.customer.controller;

import com.banking.core.dto.customer.CustomerDto;
import com.banking.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "v1/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Usage: "curl $HOST:$PORT/v1/customers".
     *
     * @return the list of customers, an empty list if there is no customer
     */
    @Operation(
            summary = "${api.customers.get-all.description}",
            description = "${api.customers.get-all.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    /**
     * Usage: "curl $HOST:$PORT/v1/customers/0234".
     *
     * @param customerId
     * @return the customer info, if found, else null
     */
    @Operation(
            summary = "${api.customers.get-customer-by-id.description}",
            description = "${api.customers.get-customer-by-id.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @GetMapping(value = "/{customerId}/image")
    Resource downloadImage(@PathVariable String customerId) {
        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            byte[] image = customerDto.getPhoto();
            return new ByteArrayResource(image);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/{customerId}/cert")
    Resource downloadIdPic(@PathVariable String customerId) {
        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            byte[] image = customerDto.getIdPic();
            return new ByteArrayResource(image);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/{customerId}/contract", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> exportToPdf(@PathVariable String customerId) throws IOException {
        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            HttpHeaders headers = new HttpHeaders();

            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + customerDto.getCustomerName() + ".pdf");

            byte[] imageBytes = customerDto.getContractPic();

            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "${api.customers.create-customer.description}",
            description = "${api.customers.create-customer.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Posting a new customer.....{}",customerDto);
        return ResponseEntity.ok(customerService.createCustomer(customerDto));

    }

    @Operation(
            summary = "${api.customers.update-customer.description}",
            description = "${api.customers.update-customer.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{customerId}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto) {
        if (customerService.findById(customerId) != null) {
            return ResponseEntity.ok(customerService.update(customerDto));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }


    @Operation(
            summary = "${api.customers.upload-image.description}",
            description = "${api.customers.upload-image.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{customerId}/image")
    ResponseEntity<CustomerDto> uploadImage(@PathVariable("customerId") String customerId, @RequestPart("image") MultipartFile multipartImage) throws Exception {

        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            return ResponseEntity.ok(customerService.uploadImage(customerId, multipartImage.getBytes()));

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "${api.customers.upload-cert.description}",
            description = "${api.customers.upload-cert.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{customerId}/cert")
    ResponseEntity<CustomerDto> uploadIdPic(@PathVariable("customerId") String customerId, @RequestPart("cert") MultipartFile multipartImage) throws Exception {

        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            return ResponseEntity.ok(customerService.uploadIdPic(customerId, multipartImage.getBytes()));

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "${api.customers.upload-contract.description}",
            description = "${api.customers.upload-contract.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{customerId}/contract")
    ResponseEntity<CustomerDto> uploadContract(@PathVariable("customerId") String customerId, @RequestPart("contract") MultipartFile multipartImage) throws Exception {

        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            return ResponseEntity.ok(customerService.uploadContract(customerId, multipartImage.getBytes()));

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    /**
     * Sample usage: "curl -X DELETE $HOST:$PORT/v1/customers/0234".
     *
     * @param customerId of the customer
     */
    @Operation(
            summary = "${api.customers.delete-customer.description}",
            description = "${api.customers.delete-customer.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
            @ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(String customerId) {
        customerService.deleteCustomer(customerId);
    }
}
