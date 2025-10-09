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

class PurchaseRequisitionRoutesTest {

    private fun Application.testModule() {
        install(ContentNegotiation) { json() }
        routing {
            // pastikan nama fungsi ini sama persis dengan yang ada di routes kamu
            purchaseRequisitionRoutes()
        }
    }

    // Contoh data sesuai model kamu
    private val requester = User(
        id = 3,
        name = "Requester",
        email = "requester@company.com",
        role = Role.REQUESTER // sesuaikan dengan enum Role yang kamu punya
    )

    private val items = listOf(
        Item(id = 1, name = "Pen Gel", quantity = 2, unitPrice = 5000.0),
        Item(id = 2, name = "Notebook A5", quantity = 1, unitPrice = 15000.0)
    )

    @Test
    fun `POST create requisition`() = testApplication {
        application { testModule() }

        val pr = PurchaseRequisition(
            id = 0,
            title = "Office Supplies",
            requester = requester,
            items = items,
            // status default = "Pending", remarks = null
        )

        val response = client.post("/requisition/create") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(pr))
        }

        assertTrue(
            response.status == HttpStatusCode.OK ||
                    response.status == HttpStatusCode.Created
        )

        val body = response.bodyAsText()
        // cek minimal—jangan kunci ke string tertentu
        assertTrue(
            body.contains("\"title\":\"Office Supplies\"") ||
                    body.contains("\"status\"")
        )
    }

    @Test
    fun `GET all requisitions`() = testApplication {
        application { testModule() }

        val res = client.get("/requisition/all")
        assertEquals(HttpStatusCode.OK, res.status)
        // opsional: periksa body format array
        val text = res.bodyAsText().trim()
        assertTrue(text.startsWith("[") && text.endsWith("]"))
    }
}
