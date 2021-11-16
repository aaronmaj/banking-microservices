package com.banking.transaction.aspect;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class PerformanceMonitorAdvisor  extends DefaultPointcutAdvisor {
    private static final long serialVersionUID = -3049371771366224728L;

    public PerformanceMonitorAdvisor(PerformanceMonitorInterceptor interceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.banking.transaction.aspect.TransactionMonitoringAspect.transfer()");
        this.setPointcut(pointcut);
        this.setAdvice(interceptor);
    }
}
