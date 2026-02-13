package fi.kranu.servicec
import jakarta.persistence.*

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val fromAccount: String,
    val toAccount: String,
    val value: Float
) {
    // No-arg constructor for JPA
    constructor() : this(null, "", "", 0.0f)
}
