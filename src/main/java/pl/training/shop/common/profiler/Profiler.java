package pl.training.shop.common.profiler;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Order(8)
@Aspect
@Log
public class Profiler {

    @Around("execution(* pl.training.shop.payments.FakePaymentService.process(..))")
    //@Around("bean(paymentService) || @annotation(ExecutionTime)")
    //@Around("@annotation(ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = joinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info(String.format("%s executed in %d ns", joinPoint.getSignature(), totalTime));
        return result;
    }

}
