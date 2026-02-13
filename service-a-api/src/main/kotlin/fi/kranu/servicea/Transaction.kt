package fi.kranu.servicea

data class Transaction(
    val fromAccount: String,
    val toAccount: String,
    val value: Float
)
