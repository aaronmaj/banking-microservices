package com.banking.transaction.config;

import com.banking.transaction.repository.TransactionRepository;
import com.banking.transaction.service.TransactionService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.*;

import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
public class JDKProxyConfig {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    public TransactionService transactionService(TransactionRepository transactionRepository){
        return new TransactionService(transactionRepository);
    }


}
