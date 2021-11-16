package com.banking.transaction.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionMonitoringAspect {

    @Pointcut("execution(* com.banking.transaction.service .TransactionService.transfer(..))")
    public void transfer() { }

}
