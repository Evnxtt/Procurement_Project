package com.example.procurement

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*

// import semua route extension
import com.example.procurement.routes.purchaseRequisitionRoutes
import com.example.procurement.routes.rfqRoutes
import com.example.procurement.routes.purchaseOrderRoutes
import com.example.procurement.routes.goodsReceiptRoutes
import com.example.procurement.routes.invoiceRoutes
import com.example.procurement.routes.paymentRoutes
import com.example.procurement.routes.procurementRoutes

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(CORS) {
            allowMethod(io.ktor.http.HttpMethod.Options)
            allowMethod(io.ktor.http.HttpMethod.Get)
            allowMethod(io.ktor.http.HttpMethod.Post)
            allowMethod(io.ktor.http.HttpMethod.Put)
            allowMethod(io.ktor.http.HttpMethod.Delete)
            allowHeader(io.ktor.http.HttpHeaders.Authorization)
            allowHeader(io.ktor.http.HttpHeaders.ContentType)
            allowCredentials = true
            anyHost() // In production, replace with specific allowed origins
        }
        
        install(ContentNegotiation) { json() }

        procurementRoutes()

        routing {
            purchaseRequisitionRoutes()
            rfqRoutes()
            purchaseOrderRoutes()
            goodsReceiptRoutes()
            invoiceRoutes()
            paymentRoutes()
        }
    }.start(wait = true)
}