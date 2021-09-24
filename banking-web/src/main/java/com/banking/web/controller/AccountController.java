package com.banking.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/account")
@SessionAttributes("bankingAccounts")
public class AccountController {


    @ModelAttribute
    public void addAccountsToModel(Model model){

    }
}
