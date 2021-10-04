package com.banking.account.aspect;

import com.banking.account.service.AccountService;
import com.banking.core.dto.account.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

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
    public void doBeforeSave(AccountDto account, AccountService service) {
        log.info("[beforeSave]: ---> Target object {}", service.getClass());

    }

    @AfterReturning(value = "execution(* com.banking.account.service.*Service+.createAccount(..))", returning = "result")
    public void afterServiceSave(JoinPoint joinPoint, AccountDto result) {
        log.info("[afterServiceSave]: ---> Target object {}", joinPoint.getTarget().getClass());
        log.info("[afterServiceSave]: ---> Was account saved? {}", (result != null));
    }

    @Around("execution(* com.banking.account.service.*.*(..))")
    public Object aroundProcessing(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        log.info("[aroundProcessing]: ----> Intercepting call of {}", methodName);
        log.info("[aroundProcessing] : call {} with parameters {}",methodName, Arrays.asList(arguments));
        try {
            var obj = joinPoint.proceed();

            return obj != null ? obj : Optional.empty();
        } finally {
            log.info("[aroundProcessing: ----> {} executed successfully", methodName);
        }
    }
}
