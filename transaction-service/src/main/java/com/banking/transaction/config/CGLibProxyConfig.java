package com.banking.transaction.config;

import com.banking.transaction.repository.TransactionRepository;
import com.banking.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class CGLibProxyConfig {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    //@Scope(proxyMode=ScopedProxyMode.NO)
    //@Scope(proxyMode=ScopedProxyMode.INTERFACES)
    //@Scope(proxyMode=ScopedProxyMode.DEFAULT)
    TransactionService transactionService(TransactionRepository transactionRepository){
        return  new TransactionService(transactionRepository);
    }

}
