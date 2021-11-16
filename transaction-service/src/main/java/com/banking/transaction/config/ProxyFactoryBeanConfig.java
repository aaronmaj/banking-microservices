package com.banking.transaction.config;

import com.banking.transaction.service.TransactionService;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryBeanConfig {

    @Bean
    ProxyFactoryBean transferService(){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetClass(TransactionService.class);
        proxyFactoryBean.addAdvisor(transferServiceAdvisor());
        proxyFactoryBean.setExposeProxy(true);
        return proxyFactoryBean;
    }

    @Bean
    Advisor transferServiceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.banking.transaction.service.TransactionService.checkBalance(..)");

        return new DefaultPointcutAdvisor(pointcut, new TransferInterceptor());

    }
}
