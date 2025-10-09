    package com.example.procurement.routes

    import io.ktor.server.application.*
    import io.ktor.server.request.*
    import io.ktor.server.response.*
    import io.ktor.server.routing.*
    import com.example.procurement.model.GoodsReceiptNote
    import com.example.procurement.service.GoodsReceiptService

    fun Route.goodsReceiptRoutes() {
        val service = GoodsReceiptService()

        route("/grn") {
            post("/receive") {
                val grn = call.receive<GoodsReceiptNote>()
                call.respond(service.receiveGoods(grn))
            }

            get("/all") {
                call.respond(service.getAllReceipts())
            }
        }
    }
