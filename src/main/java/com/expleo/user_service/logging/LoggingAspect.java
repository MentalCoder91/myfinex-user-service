package com.expleo.user_service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
	public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


	@Around("@annotation(com.expleo.user_service.logging.LogExecutionTime)")
	public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();


		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();


		StopWatch stopWatch = new StopWatch(className + "->" + methodName);
		stopWatch.start(methodName);
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		
		long executionTimeMillis = stopWatch.getTotalTimeMillis();
        if (logger.isInfoEnabled()) {
            logger.info("Execution time for {}: {} ms", className + "->" + methodName, executionTimeMillis);
        }
		return result;
	}
	
	

	
}