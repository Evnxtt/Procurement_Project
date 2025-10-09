//package com.example.procurement.routes
//
//import com.example.procurement.model.Payment
//import com.example.procurement.model.User
//import com.example.procurement.model.Role
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
//import io.ktor.server.testing.*
//import io.ktor.serialization.kotlinx.json.*
//import io.ktor.server.application.*
//import io.ktor.server.plugins.contentnegotiation.*
//import io.ktor.server.routing.*
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//import org.junit.jupiter.api.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertTrue
//
//class PaymentRoutesTest {
//
//    private fun Application.testModule() {
//        install(ContentNegotiation) {
//            json()
//        }
//        routing {
//            paymentRoutes()
//        }
//    }
//
//    private val user = User(
//        id = 1,
//        name = "John Doe",
//        email = "john@company.com",
//        role = Role.FINANCE
//    )
//
//    @Test
//    fun `should make a payment successfully`() = testApplication {
//        application { testModule() }
//
//        val payment = Payment(
//            id = 0,
//            invoiceId = 101,
//            amount = 5000.0,
//            processedBy = user,
//            status = "Pending",
//            paymentDate = null // set null to avoid extra serializer setup in test
//        )
//
//        val response = client.post("/payment/make") {
//            contentType(ContentType.Application.Json)
//            setBody(Json.encodeToString(payment))
//        }
//
//        assertEquals(HttpStatusCode.OK, response.status)
//        val responseBody = response.bodyAsText()
//        // periksa respons minimal — bisa berisi objek payment yang dikembalikan
//        assertTrue(responseBody.contains("\"invoiceId\":101") || responseBody.contains("\"status\""))
//    }
//
//    @Test
//    fun `should get all payments`() = testApplication {
//        application { testModule() }
//
//        // tambahkan satu pembayaran lewat endpoint
//        val payment = Payment(
//            id = 0,
//            invoiceId = 102,
//            amount = 8000.0,
//            processedBy = user,
//            status = "Paid",
//            paymentDate = null
//        )
//
//        client.post("/payment/make") {
//            contentType(ContentType.Application.Json)
//            setBody(Json.encodeToString(payment))
//        }
//
//        val response = client.get("/payment/all")
//        assertEquals(HttpStatusCode.OK, response.status)
//        val responseBody = response.bodyAsText()
//        assertTrue(responseBody.contains("8000.0") || responseBody.contains("\"invoiceId\":102"))
//    }
//
//    @Test
//    fun `should return empty list when no payments exist`() = testApplication {
//        application { testModule() }
//
//        val response = client.get("/payment/all")
//        assertEquals(HttpStatusCode.OK, response.status)
//        val body = response.bodyAsText().trim()
//        assertTrue(body == "[]" || body.contains("[]"))
//    }
//}
