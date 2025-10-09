package com.example.procurement.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.procurement.model.PurchaseOrder
import com.example.procurement.service.PurchaseOrderService

fun Route.purchaseOrderRoutes() {
    val service = PurchaseOrderService()

    route("/po") {
        post("/create") {
            val po = call.receive<PurchaseOrder>()
            call.respond(service.createOrder(po))
        }

        get("/all") {
            call.respond(service.getAllOrders())
        }

        put("/{id}/approve") {
            val id = call.parameters["id"]?.toIntOrNull()
            id?.let {
                service.approveOrder(it)?.let { call.respond(it) } ?: call.respondText("Not Found")
            } ?: call.respondText("Invalid ID")
        }

        put("/{id}/reject") {
            val id = call.parameters["id"]?.toIntOrNull()
            val reason = call.receiveText()
            id?.let {
                service.rejectOrder(it, reason)?.let { call.respond(it) } ?: call.respondText("Not Found")
            } ?: call.respondText("Invalid ID")
        }
    }
}
