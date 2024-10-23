package com.artist.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.artist.service.impl.CustomersServiceImpl;

import jakarta.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Autowired
	private CustomersServiceImpl csi;
	
    @Around("execution(* com.artist.controller.PTController.*(..))") // 拦截 PTController 中的方法
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        
        // 获取当前请求
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();

        // 从请求中提取用户信息（例如从请求头或参数中）
        // 从请求头中获取 token
        String authHeader = request.getHeader("Authorization");
        String customerId = null;
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // 去掉 "Bearer " 前缀
            customerId = csi.getCustomerIdFromToken(token);
        }
        
        logger.info("User {} is calling method: {}", customerId != null ? customerId : "Unknown", methodName);

        
        Object result;
        try {
            result = joinPoint.proceed(); // 执行目标方法
        } catch (Throwable throwable) {
            logger.error("Method {} threw an exception: {}", methodName, throwable.getMessage());
            throw throwable; // 抛出异常
        }
        
        logger.info("User {} exited method: {}", customerId, methodName);
        return result;
    }
}
