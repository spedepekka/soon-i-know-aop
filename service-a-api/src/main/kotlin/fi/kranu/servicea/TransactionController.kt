package fi.kranu.servicea

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

@RestController
@RequestMapping("/transaction")
class TransactionController(private val restTemplate: RestTemplate) {

    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    @PostMapping
    fun handleUserTransaction(@RequestBody transaction: Transaction): Transaction {
        val correlationId = MDC.get("correlationId")
        logger.info("handleUserTransaction called with correlationId: $correlationId, transaction: $transaction")

        val headers = HttpHeaders()
        if (correlationId != null) {
            headers.set("X-Correlation-Id", correlationId)
        }

        val request = HttpEntity(transaction, headers)
        try {
            restTemplate.postForObject("http://service-b-lol:8080/internal-transaction", request, Transaction::class.java)
        } catch (e: Exception) {
            logger.error("Failed to call service-b", e)
        }

        return transaction
    }
}
