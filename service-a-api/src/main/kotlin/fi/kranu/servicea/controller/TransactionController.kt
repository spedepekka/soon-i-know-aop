package fi.kranu.servicea.controller

import fi.kranu.servicea.aop.CustomAuthentication
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import fi.kranu.servicea.model.Transaction
import org.springframework.http.HttpEntity

@RestController
@RequestMapping("/transaction")
class TransactionController(private val restTemplate: RestTemplate) {

    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    @CustomAuthentication
    @PostMapping
    fun handleUserTransaction(@RequestBody transaction: Transaction): Transaction {
        val correlationId = MDC.get("correlationId")
        logger.info("handleUserTransaction called with transaction: $transaction")

        // Send to other service if the client asks for it
        // This has nothing to do with real world and it is just for testing purposes
        if (transaction.forward) {
            val headers = HttpHeaders()
            if (correlationId != null) {
                headers.set("X-Correlation-Id", correlationId)
            }

            val request = HttpEntity(transaction, headers)
            try {
                restTemplate.postForObject(
                    "http://service-b-lol:8080/internal-transaction",
                    request,
                    Transaction::class.java
                )
            } catch (e: Exception) {
                logger.error("Failed to call service-b", e)
            }
        }

        return transaction
    }
}
