package com.example.procurement.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.procurement.service.ProcurementService

fun Application.procurementRoutes() {
    routing {
        route("/procurements") {
            get {
                call.respondText("List of procurements (dummy data for now)")
            }
            get("/{id}") {
                val id = call.parameters["id"] ?: return@get call.respondText("Missing ID", status = io.ktor.http.HttpStatusCode.BadRequest)
                call.respondText("Detail for procurement ID: $id")
            }
        }
    }
}
