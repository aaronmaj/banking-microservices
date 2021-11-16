package com.banking.transaction.aspect;

import com.banking.core.dto.transaction.TransactionStatus;
import com.banking.transaction.model.Transaction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.banking.transaction.service.TransactionService.transfer(..))")
    public void transfer() {

    }

    @After("transfer()")
    public void afterTransfer(JoinPoint joinPoint) {
        logger.info("Successfully transferred from source account to dest account");
    }

    @AfterReturning(pointcut = "transfer() && args(source, destination, amount))", returning = "transaction")
    public void afterTransferReturns(JoinPoint joinPoint,String source, String destination, double amount, Transaction transaction){
        if(transaction.getStatus().equals(TransactionStatus.SUCCESSFUL)){
            logger.info("Transfer of {} from {} to {} completed successfully",amount, source,destination);
        }
    }
}
