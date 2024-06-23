package com.expleo.user_service.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class MdcInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID_HEADER = "Correlation-Id";
    private static final String CORRELATION_ID_MDC_KEY = "Correlation-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);
        if (correlationId == null) {
            correlationId = getCorrelationId();
        }
        MDC.put(CORRELATION_ID_MDC_KEY, correlationId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(CORRELATION_ID_MDC_KEY);
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}