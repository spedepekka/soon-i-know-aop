package fi.kranu.servicea

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should create and return transactions`() {
        val transactionJson = """
            {
                "fromAccount": "ACC1",
                "toAccount": "ACC2",
                "value": 100.5
            }
        """.trimIndent()

        mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(transactionJson))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.fromAccount").value("ACC1"))
            .andExpect(jsonPath("$.toAccount").value("ACC2"))
            .andExpect(jsonPath("$.value").value(100.5))

        mockMvc.perform(get("/transactions"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].fromAccount").value("ACC1"))
            .andExpect(jsonPath("$[0].toAccount").value("ACC2"))
            .andExpect(jsonPath("$[0].value").value(100.5))
    }
}
