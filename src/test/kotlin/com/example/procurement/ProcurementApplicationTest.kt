package com.example.procurement

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import com.example.procurement.routes.procurementRoutes
import kotlin.test.*

class ProcurementApplicationTest {
    @Test
    fun testProcurementRoutes() = testApplication {
        application { 
            procurementRoutes()
        }

        val response = client.get("/procurements")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("List of procurements (dummy data for now)", response.bodyAsText())
    }

    @Test
    fun testProcurementById() = testApplication {
        application { 
            procurementRoutes()
        }

        val response = client.get("/procurements/123")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Detail for procurement ID: 123", response.bodyAsText())
    }
}