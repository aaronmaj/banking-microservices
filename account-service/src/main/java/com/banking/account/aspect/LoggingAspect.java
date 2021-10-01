package com.banking.account.aspect;

import com.banking.account.model.Account;
import com.banking.account.service.AccountService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.banking.account.service.*.*(..))")
    public void businessService() {

    }

    @Pointcut("execution(* com.banking.account.service.*Service+.createAccount(..)) && args(account) && target(accountService)")
    public void beforeSaving(Account account, AccountService accountService) {

    }

}
