package com.banking.transaction.config;

import com.banking.transaction.aspect.PerformanceMonitorAdvisor;
import com.banking.transaction.aspect.TransactionMonitoringAspect;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ServiceConfig {

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor()
    {
        return new PerformanceMonitorInterceptor(true);
    }
    @Bean
    public TransactionMonitoringAspect transactionMonitoringAspect() {
        return new TransactionMonitoringAspect();
    }
    @Bean
    public PerformanceMonitorAdvisor performanceMonitorAdvisor() {
        return new PerformanceMonitorAdvisor(performanceMonitorInterceptor());
    }
}
