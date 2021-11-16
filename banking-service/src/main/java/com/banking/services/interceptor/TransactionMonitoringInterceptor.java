package com.banking.services.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import java.time.LocalDate;

public class TransactionMonitoringInterceptor extends AbstractMonitoringInterceptor {

    private static final long serialVersionUID = -4060921270422590121L;
    private final boolean dynamicLogger;

    public TransactionMonitoringInterceptor() {
        dynamicLogger = false;
    }

    public TransactionMonitoringInterceptor(boolean dynamicLogger) {
        this.dynamicLogger = dynamicLogger;
        setUseDynamicLogger(dynamicLogger);
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
        String name = createInvocationTraceName(invocation);
        long start = System.currentTimeMillis();
        logger.info("Method " + name + " execution started at:" + LocalDate.now());
        try {
            return invocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.info("Method " + name + " execution lasted:" + time + " ms");
            logger.info("Method " + name + " execution ended at:" + LocalDate.now());
            if (time > 5) {
                logger.warn("Method execution took longer than 5 ms!");
            }
        }

    }
}

