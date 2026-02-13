package fi.kranu.servicea

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CopyOnWriteArrayList

@RestController
@RequestMapping("/transaction")
class TransactionController(private val restTemplate: RestTemplate) {

    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    @PostMapping
    fun handleUserTransaction(@RequestBody transaction: Transaction): Transaction {
        logger.info("handleUserTransaction called ${transaction}")
        restTemplate.postForObject("http://service-b-lol:8080/internal-transaction", transaction, Transaction::class.java)
        return transaction
    }

//    @GetMapping
//    fun getAllTransactions(): List<Transaction> {
//        return transactions
//    }
}
