package com.example.procurement.routes

import com.example.procurement.model.*
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

class InvoiceroutesTest1 {

    private fun Application.testModule() {
        install(ContentNegotiation) { json() }
        routing { invoiceRoutes() }
    }

    private val vendor = Vendor(1, "PT Sumber Makmur", "contact@sumber.com", "08123456789")
    private val creator = User(2, "Creator", "creator@co.com", Role.PROCUREMENT_OFFICER)

    @Test
    fun `POST create invoice`() = testApplication {
        application { testModule() }
        val invoice = Invoice(
            id = 0,
            vendor = vendor,
            relatedPO = 777,
            totalAmount = 5000000.0,
            createdBy = creator,
            approvedBy = null,
            status = "Unverified",
            remarks = null
        )

        val res = client.post("/invoice/create") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(invoice))
        }
        assertTrue(res.status == HttpStatusCode.OK || res.status == HttpStatusCode.Created)
        assertTrue(res.bodyAsText().contains("\"relatedPO\":777") || res.bodyAsText().contains("\"status\""))
    }

    @Test
    fun `GET all invoices`() = testApplication {
        application { testModule() }
        val res = client.get("/invoice/all")
        assertEquals(HttpStatusCode.OK, res.status)
    }
}
