package fi.kranu.serviceb

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import kotlin.random.Random

@RestController
@RequestMapping("/internal-transaction")
class TransactionController (private val restTemplate: RestTemplate){
    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    @PostMapping
    fun handleTransaction(@RequestBody transaction: Transaction): Transaction {
        val correlationId = MDC.get("correlationId")
        logger.info("handleTransaction called with correlationId: $correlationId, transaction: $transaction")

        // Randomize between 500 ms and 3000 ms
        val delay = Random.nextInt(500,3000)
        logger.info("Waiting for {} ms", delay)
        // Simulate transaction processing with random delay
        try {
            Thread.sleep((delay).toLong())
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            throw RuntimeException("Transaction handling interrupted", e)
        }

        val response = restTemplate.postForObject("http://service-c-db:8080/database-transaction", transaction, Transaction::class.java)

        return response ?: throw RuntimeException("Failed to process transaction in service B")
    }
}
