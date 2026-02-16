package fi.kranu.servicea.model

data class Transaction(
    val fromAccount: String,
    val toAccount: String,
    val value: Float,
    // Just a dirty switch to send the request to other service or not
    val forward: Boolean = false
)
