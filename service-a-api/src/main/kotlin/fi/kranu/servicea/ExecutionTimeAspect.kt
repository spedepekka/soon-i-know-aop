package fi.kranu.servicea

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class ExecutionTimeAspect {

    private val logger = LoggerFactory.getLogger(ExecutionTimeAspect::class.java)

    // This is just an example of how to use AOP to log execution time from single method.
    // In real application you might want to log execution time from multiple methods, all methods in a package/application
    // or even between microservices.
    @Around("execution(* fi.kranu.servicea.TransactionController.handleUserTransaction(..))")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        val proceed = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - start
        logger.info("${joinPoint.signature} executed in ${executionTime}ms")
        return proceed
    }
}
