package com.example.procurement.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.procurement.model.RequestForQuotation
import com.example.procurement.service.RequestForQuotationService

fun Route.rfqRoutes() {
    val service = RequestForQuotationService()

    route("/rfq") {
        post("/create") {
            val rfq = call.receive<RequestForQuotation>()
            call.respond(service.createRFQ(rfq))
        }

        get("/all") {
            call.respond(service.getAllRFQs())
        }

        put("/{id}/status") {
            val id = call.parameters["id"]?.toIntOrNull()
            val newStatus = call.receiveText()
            id?.let {
                service.updateStatus(it, newStatus)?.let { call.respond(it) } ?: call.respondText("Not Found")
            } ?: call.respondText("Invalid ID")
        }
    }
}
