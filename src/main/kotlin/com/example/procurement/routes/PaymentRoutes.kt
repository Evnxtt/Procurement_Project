package com.example.procurement.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.procurement.model.Payment
import com.example.procurement.service.PaymentService

fun Route.paymentRoutes() {
    val service = PaymentService()

    route("/payment") {
        post("/make") {
            val payment = call.receive<Payment>()
            call.respond(service.makePayment(payment))
        }

        get("/all") {
            call.respond(service.getAllPayments())
        }
    }
}
