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

class PurchaseOrderRoutesTest {

    private fun Application.testModule() {
        install(ContentNegotiation) { json() }
        routing { purchaseOrderRoutes() }
    }

    private val vendor = Vendor(1, "PT Vendor", "v@vendor.com", "0811")
    private val creator = User(1, "Requester", "req@co.com", Role.REQUESTER)
    private val items = listOf(Item(1, "Laptop", 1, 15000000.0))

    @Test
    fun `POST create po`() = testApplication {
        application { testModule() }
        val po = PurchaseOrder(
            id = 0,
            vendor = vendor,
            items = items,
            createdBy = creator,
            approvedBy = null,
            status = "Draft",
            remarks = null
        )

        val res = client.post("/po/create") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(po))
        }
        assertTrue(res.status == HttpStatusCode.OK || res.status == HttpStatusCode.Created)
        assertTrue(res.bodyAsText().contains("\"items\"") || res.bodyAsText().contains("\"status\""))
    }

    @Test
    fun `GET all po`() = testApplication {
        application { testModule() }
        val res = client.get("/po/all")
        assertEquals(HttpStatusCode.OK, res.status)
    }
}
