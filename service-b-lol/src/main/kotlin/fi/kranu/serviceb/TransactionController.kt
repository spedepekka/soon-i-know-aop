package fi.kranu.serviceb

import org.springframework.web.bind.annotation.*
import java.util.concurrent.CopyOnWriteArrayList

@RestController
@RequestMapping("/transactions")
class TransactionController {
    private val transactions = CopyOnWriteArrayList<Transaction>()

    @PostMapping
    fun createTransaction(@RequestBody transaction: Transaction): Transaction {
        transactions.add(transaction)
        return transaction
    }

    @GetMapping
    fun getAllTransactions(): List<Transaction> {
        return transactions
    }
}
