package fi.kranu.servicec

data class Transaction(
    val fromAccount: String,
    val toAccount: String,
    val value: Float
)
