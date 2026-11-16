
package com.restaurante.sistemapedidos.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.restaurante.sistemapedidos.service..*(..))")
    public Object medirTiempo(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long inicio = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long duracion =
                    System.currentTimeMillis() - inicio;

            logger.info("AOP - Metodo: {} - Tiempo: {} ms",
                    joinPoint.getSignature().getName(),
                    duracion);
        }
    }
}
