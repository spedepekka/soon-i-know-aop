package fi.kranu.servicec

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
@RequestMapping("/database-transaction")
class DatabaseController(private val transactionRepository: TransactionRepository) {
    private val logger = LoggerFactory.getLogger(DatabaseController::class.java)

    @PostMapping
    fun handleTransaction(@RequestBody transaction: Transaction): Transaction {
        logger.info("Saving transaction from {} to {}", transaction.fromAccount, transaction.toAccount)
        return transactionRepository.save(transaction)
    }
}
