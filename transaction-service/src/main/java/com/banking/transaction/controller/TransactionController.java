package com.banking.transaction.controller;

import com.banking.core.dto.transaction.TransactionDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/transactions")
@CrossOrigin
public class TransactionController {

    @RequestMapping(method = RequestMethod.GET)
    public List<TransactionDto> findAll(){
        return null;
    }
}
