package com.example.demo;

import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class AdderAfterReturnAspect {


    //@Before("execution(* com.example.demo.SimpleAdder.add.*.*(..))")

    @Before("execution(* com.example.demo.SimpleAdder.add*.*(..))")

    public void afterReturn() throws Throwable {
        log.info("Aspect executed");
        System.out.println("Aspect executed0 system");
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.SimpleAdder.add*(..))", returning = "result")
    public void afterReturn(Object result) throws Throwable {
        log.info("Method returned: " + result);
        System.out.println("Aspect executed1 system");
    }

}
