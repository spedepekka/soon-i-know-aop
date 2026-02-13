package fi.kranu.servicea

import org.slf4j.LoggerFactory
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
        logger.info("handleUserTransaction called ${transaction}")

        val correlationId = UUID.randomUUID().toString()
        val headers = HttpHeaders()
        headers.set("X-Correlation-ID", correlationId)
        logger.info("Correlation ID: $correlationId")

        val request = HttpEntity(transaction, headers)

        restTemplate.postForObject("http://service-b-lol:8080/internal-transaction", request, Transaction::class.java)
        return transaction
    }
}
