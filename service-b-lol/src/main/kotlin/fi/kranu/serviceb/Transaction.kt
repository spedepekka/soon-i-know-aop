package fi.kranu.serviceb

data class Transaction(
    val fromAccount: String,
    val toAccount: String,
    val value: Float
)
