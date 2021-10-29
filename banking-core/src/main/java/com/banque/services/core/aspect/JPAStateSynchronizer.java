package com.banque.services.core.aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.annotation.Aspect;

@Component
@Aspect
public class JPAStateSynchronizer {

    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* javax.persistence.EntityManager.*(..))")
    public void flush(){
        if(entityManager!=null){
            entityManager.flush();
        }
    }
}
