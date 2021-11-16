package com.banking.transaction.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;

/**
 * All other aspect are singletons by default
 * This aspect store the status of methode calls.
 * Expressed with perthis expression
 * Spring scope prototype –i.e. multiple instances possible
 *
 * Once an @Aspect is declared with the perthis clause, one aspect instance will be created
 * for each unique TransferService object executing the transfer method
 * (each unique object that is bound to this at join points matched by the pointcut expression)
 */
@Aspect("perthis(com.banking.transaction.service.TransactionService.transfer())")
@Scope("prototype")
public class TransferAspect {

    //Add per instance attributes holding private data thus making the aspect stateful
    private boolean isSuccessfull; //private attribute holding data relative to class instances
    //Define your advice methods
}
