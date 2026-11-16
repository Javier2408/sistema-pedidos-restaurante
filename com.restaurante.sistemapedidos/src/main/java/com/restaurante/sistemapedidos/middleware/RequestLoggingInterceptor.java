
package com.restaurante.sistemapedidos.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        logger.info("Solicitud recibida: {} {}",
                request.getMethod(),
                request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        logger.info("Solicitud finalizada: {} {} - Estado: {}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus());

        if (ex != null) {
            logger.error("Error durante la solicitud", ex);
        }
    }
}
