package com.banking.account.aspect;

import com.banking.account.model.Account;
import com.banking.account.service.AccountService;
import com.banking.core.dto.account.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.banking.account.service.*.*(..))")
    public void businessService() {

    }

    @Pointcut("execution(* com.banking.account.service.*Service+.createAccount(..)) && args(account) && target(accountService)")
    public void beforeSaving(AccountDto account, AccountService accountService) {

    }

    @Before("com.banking.account.aspect.LoggingAspect.beforeSaving(account,service)")
    public void doBeforeSave(AccountDto account, AccountService service){
        log.info("[beforeSave]: ---> Target object {}", service.getClass());

    }

    @AfterReturning(value = "execution(* com.banking.account.service.*Service+.createAccount(..))", returning = "result")
    public void afterServiceSave(JoinPoint joinPoint, AccountDto result){
        log.info("[afterServiceSave]: ---> Target object {}",  joinPoint.getTarget().getClass());
        log.info("[afterServiceSave]: ---> Was account saved? {}", (result != null));
    }
}
