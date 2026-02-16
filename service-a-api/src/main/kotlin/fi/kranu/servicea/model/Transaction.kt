package fi.kranu.servicea.model

data class Transaction(
    val fromAccount: String,
    val toAccount: String,
    val value: Float
)
