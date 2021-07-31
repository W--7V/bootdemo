package springboot.demo.aop;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class LogAdvice {
	@Around("execution(public * springboot.demo.controller.*Controller.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		MDC.put("trace_uuid", UUID.randomUUID().toString().replace("-", ""));
		Object result = pjp.proceed();
		return result;
	}
}
