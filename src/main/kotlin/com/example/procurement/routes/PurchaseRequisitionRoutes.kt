package com.example.procurement.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.procurement.model.PurchaseRequisition
import com.example.procurement.service.PurchaseRequisitionService

fun Route.purchaseRequisitionRoutes() {
    val service = PurchaseRequisitionService()

    route("/requisition") {

        post("/create") {
            val req = call.receive<PurchaseRequisition>()
            call.respond(service.createRequisition(req))
        }

        get("/all") {
            call.respond(service.getAllRequisitions())
        }

        put("/{id}/approve") {
            val id = call.parameters["id"]?.toIntOrNull()
            id?.let {
                service.approveRequisition(it)?.let { call.respond(it) } ?: call.respondText("Not Found")
            } ?: call.respondText("Invalid ID")
        }

        put("/{id}/reject") {
            val id = call.parameters["id"]?.toIntOrNull()
            val reason = call.receiveText()
            id?.let {
                service.rejectRequisition(it, reason)?.let { call.respond(it) } ?: call.respondText("Not Found")
            } ?: call.respondText("Invalid ID")
        }
    }
}
