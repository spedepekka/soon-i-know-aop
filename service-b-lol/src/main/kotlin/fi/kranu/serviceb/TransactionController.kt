package fi.kranu.serviceb

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.random.Random

@RestController
@RequestMapping("/internal-transactions")
class TransactionController {
    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    @PostMapping
    fun handleTransaction(@RequestBody transaction: Transaction): Transaction {
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

        return transaction
    }
}
