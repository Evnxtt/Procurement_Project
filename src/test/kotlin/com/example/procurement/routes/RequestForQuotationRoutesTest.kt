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
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RequestForQuotationRoutesTest {

    private fun Application.testModule() {
        install(ContentNegotiation) { json() }
        routing { rfqRoutes() }
    }

    private val vendor = Vendor(2, "PT Office", "office@supplies.com", "0834")
    private val creator = User(7, "Officer", "officer@co.com", Role.PROCUREMENT_OFFICER)
    private val items = listOf(Item(10, "Desk", 2, 800000.0))

    @Test
    fun `POST create rfq`() = testApplication {
        application { testModule() }
        val rfq = RequestForQuotation(
            id = 0,
            vendor = vendor,
            requestedItems = items,
            createdBy = creator,
            status = "Sent",
            remarks = null
        )

        val res = client.post("/rfq/create") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(rfq))
        }
        assertTrue(res.status == HttpStatusCode.OK || res.status == HttpStatusCode.Created)
        assertTrue(res.bodyAsText().contains("\"requestedItems\"") || res.bodyAsText().contains("\"status\""))
    }

    @Test
    fun `GET all rfq`() = testApplication {
        application { testModule() }
        val res = client.get("/rfq/all")
        assertEquals(HttpStatusCode.OK, res.status)
    }
}
