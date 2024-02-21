package by.intereson.ebookservice.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static by.intereson.ebookservice.utils.Constants.*;

@Slf4j
@Aspect
@Component
public class LoggerAspect {


    @Pointcut("execution(* by.intereson.ebookservice.controllers..*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest servletRequest = getServletRequest();
        log.info(LOG_REQUEST_PATTERN,
                servletRequest.getMethod(),
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs(),
                servletRequest.getRequestURI());
    }

    @AfterReturning(value = "pointCut()", returning = RESPONSE, argNames = "joinPoint,response")
    public void logResponse(JoinPoint joinPoint, Object response) {
        HttpServletRequest servletRequest = getServletRequest();
        log.info(LOG_RESPONSE_PATTERN,
                servletRequest.getMethod(),
                joinPoint.getSignature().toShortString(),
                servletRequest.getRequestURI(),
                response);
    }

    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
