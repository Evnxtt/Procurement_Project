package com.example.procurement.routes

import com.example.procurement.model.Payment
import com.example.procurement.model.Role
import com.example.procurement.model.User
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class PaymentRoutesTest1 {

    private fun Application.testModule() {
        install(ContentNegotiation) { json() }
        routing { paymentRoutes() }
    }

    private val finance = User(1, "Finance", "finance@co.com", Role.FINANCE)

    @Test
    fun `POST make payment`() = testApplication {
        application { testModule() }
        val body = Payment(0, invoiceId = 1001, amount = 150000.0, processedBy = finance, status = "Pending", paymentDate = null)

        val res = client.post("/payment/make") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(body))
        }
        assertEquals(HttpStatusCode.OK, res.status)
        assertTrue(res.bodyAsText().contains("\"invoiceId\":1001") || res.bodyAsText().contains("\"status\""))
    }

    @Test
    fun `GET all payments`() = testApplication {
        application { testModule() }
        val res = client.get("/payment/all")
        assertEquals(HttpStatusCode.OK, res.status)
    }
}
