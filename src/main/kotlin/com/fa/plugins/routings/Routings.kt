package com.fa.plugins.routings

import com.fa.data.models.InvestorDTO
import com.fa.domain.usecases.investors.*
import com.fa.domain.util.GsonUtil.asJson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Routing.insertInvestor(useCase: InsertInvestorUseCase) {
    post("/insert-investor") {
        try {
            val receivedBody = call.receive<InvestorDTO>()

            val result = useCase.invoke(
                investorDTO = receivedBody
            )

            if (result) {
                call.respond(status = HttpStatusCode.Created, "OK ${receivedBody.id}.")
            } else {
                call.respond(status = HttpStatusCode.BadRequest, "NOT OK.")
            }
        } catch (e: Exception) {
            call.respond(status = HttpStatusCode.BadRequest, e.message ?: "NOK.")
        }
    }
}

fun Routing.allInvestors(useCase: AllInvestorsUseCase) {
    get("/all-investors") {
        val allInvestors = useCase.invoke()
        if (allInvestors.isEmpty()) {
            call.respond(status = HttpStatusCode.BadRequest, "Sorry")
            return@get
        } else {
            call.respond(status = HttpStatusCode.OK, allInvestors.asJson())
        }
    }
}

fun Routing.getInvestorById(useCase: GetInvestorByIdUseCase) {
    get("/investor-by-id") {
        val id = call.request.queryParameters["id"]

        if (id == null) {
            call.respond(status = HttpStatusCode.NotFound, "Sorry2")
            return@get
        }

        val investor = useCase.invoke(id)

        if (investor == null) {
            call.respond(status = HttpStatusCode.NotFound, "Sorry3")
            return@get
        }

        call.respond(status = HttpStatusCode.OK, investor.asJson())
    }
}

fun Routing.putInvestor(useCase: PutInvestorUseCase) {
    put("/put-investor") {
        try {
            val receivedId = call.request.queryParameters["id"] ?: throw IllegalArgumentException()
            val receivedBody = call.receive<InvestorDTO>()
            val result = useCase.invoke(id = receivedId, investor = receivedBody)

            if (result != 0L) {
                call.respond(status = HttpStatusCode.OK, "OK: $result")
            } else {
                call.respond(status = HttpStatusCode.NotFound, "NOK.")
            }
        } catch (e: Exception) {
            call.respond(status = HttpStatusCode.BadRequest, e.message ?: "boş")
        }
    }
}

fun Routing.deleteInvestor(useCase: DeleteInvestorUseCase) {
    delete("/delete-investor") {
        val id = call.request.queryParameters["id"]

        if (id == null) {
            call.respond(status = HttpStatusCode.NotFound, "Sorry2")
            return@delete
        }

        val result = useCase.invoke(id = ObjectId(id))

        if (!result) {
            call.respond(status = HttpStatusCode.NotFound, "Sorry3")
            return@delete
        }

        call.respond(status = HttpStatusCode.OK, "Deleted by id.")
    }
}