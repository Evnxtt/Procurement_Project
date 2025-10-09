package com.example.procurement.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import io.ktor.serialization.kotlinx.json.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProcurementRoutesTest {

    private fun Application.testModule() {
        install(ContentNegotiation) { json() }
        procurementRoutes()
    }

    @Test
    fun `GET list procurements`() = testApplication {
        application { testModule() }
        val res = client.get("/procurements")
        assertEquals(200, res.status.value)
        assertTrue(res.bodyAsText().contains("List of procurements"))
    }

    @Test
    fun `GET procurement by id`() = testApplication {
        application { testModule() }
        val res = client.get("/procurements/123")
        assertEquals(200, res.status.value)
        assertTrue(res.bodyAsText().contains("123"))
    }

    @Test
    fun `GET procurements trailing slash NotFound`() = testApplication {
        application { testModule() }
        val res = client.get("/procurements/")
        assertEquals(404, res.status.value)
    }
}
