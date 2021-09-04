package com.banking.customer.controller;

import com.banking.core.dto.customer.CustomerDto;
import com.banking.customer.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

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

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));

    }

    @PutMapping(value = "/{customerId}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto) {
        if (customerService.findById(customerId) != null) {
            return ResponseEntity.ok(customerService.update(customerDto));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PutMapping(value = "/{customerId}/image")
    ResponseEntity<CustomerDto> uploadImage(@PathVariable("customerId") String customerId, @RequestPart("image") MultipartFile multipartImage) throws Exception {

        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            return ResponseEntity.ok(customerService.uploadImage(customerId, multipartImage.getBytes()));

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{customerId}/cert")
    ResponseEntity<CustomerDto> uploadIdPic(@PathVariable("customerId") String customerId, @RequestPart("cert") MultipartFile multipartImage) throws Exception {

        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            return ResponseEntity.ok(customerService.uploadIdPic(customerId, multipartImage.getBytes()));

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{customerId}/contract")
    ResponseEntity<CustomerDto> uploadContract(@PathVariable("customerId") String customerId, @RequestPart("contract") MultipartFile multipartImage) throws Exception {

        CustomerDto customerDto = customerService.findById(customerId);
        if (customerDto != null) {
            return ResponseEntity.ok(customerService.uploadContract(customerId, multipartImage.getBytes()));

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(String customerId) {
        customerService.deleteCustomer(customerId);
    }
}
