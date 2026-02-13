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

    @Around("execution(* fi.kranu.servicea.TransactionController.handleUserTransaction(..))")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        val proceed = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - start
        logger.info("${joinPoint.signature} executed in ${executionTime}ms")
        return proceed
    }
}
