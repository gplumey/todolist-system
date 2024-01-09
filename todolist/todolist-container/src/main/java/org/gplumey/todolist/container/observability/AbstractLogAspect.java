package org.gplumey.todolist.container.observability;

import jakarta.validation.constraints.NotNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

public class AbstractLogAspect {

    private static LogInfo getLogInfo(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Class declaringType = signature.getDeclaringType();
        String className = declaringType.getSimpleName();
        String annotatedMethodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        return new LogInfo(declaringType, className, annotatedMethodName, args);
    }

    public void logBefore(ProceedingJoinPoint joinPoint) {
        LogInfo logInfo = getLogInfo(joinPoint);
        // this make the logger print the right classType
        Logger log = LoggerFactory.getLogger(logInfo.declaringType);
        log.info("[{}.{}] start ({})", logInfo.className,
                logInfo.annotatedMethodName, logInfo.args);
    }

    public void logAfter(ProceedingJoinPoint joinPoint) {
        LogInfo logInfo = getLogInfo(joinPoint);
        Logger log = LoggerFactory.getLogger(logInfo.declaringType);
        log.info("[{}.{}] end", logInfo.className, logInfo.annotatedMethodName);
    }

    private record LogInfo(
            @NotNull
            Class declaringType,
            @NotNull
            String className,
            @NotNull
            String annotatedMethodName,
            @Nullable
            Object[] args) {
    }
}
