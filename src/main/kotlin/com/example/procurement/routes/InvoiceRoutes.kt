package com.example.procurement.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.procurement.model.Invoice
import com.example.procurement.service.InvoiceService

fun Route.invoiceRoutes() {
    val service = InvoiceService()

    route("/invoice") {
        post("/create") {
            val invoice = call.receive<Invoice>()
            call.respond(service.createInvoice(invoice))
        }

        get("/all") {
            call.respond(service.getAllInvoices())
        }

        put("/{id}/reject") {
            val id = call.parameters["id"]?.toIntOrNull()
            val reason = call.receiveText()
            id?.let {
                service.rejectInvoice(it, reason)?.let { call.respond(it) } ?: call.respondText("Not Found")
            } ?: call.respondText("Invalid ID")
        }
    }
}
