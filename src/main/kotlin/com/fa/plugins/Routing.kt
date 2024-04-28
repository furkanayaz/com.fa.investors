package com.fa.plugins

import com.fa.data.models.Investor
import com.fa.domain.usecases.investors.AllInvestorsUseCase
import com.fa.domain.usecases.investors.InsertInvestorUseCase
import com.fa.domain.util.GsonUtil.asJson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val allInvestorsUseCase by inject<AllInvestorsUseCase>()
    val insertInvestorUseCase by inject<InsertInvestorUseCase>()

    routing {
        swaggerUI(path = "/api", swaggerFile = "openapi/documentation.yaml")

        post("/insert-investor") {
            val model = Investor(id = ObjectId(), fullName = "Furkan A.", age = 23, netWorth = 100)
            val result = insertInvestorUseCase.invoke(investor = model)

            if (result) {
                call.respond(status = HttpStatusCode.Created, "OK ${model.id}.")
            } else {
                call.respond(status = HttpStatusCode.BadRequest, "NOT OK.")
            }
        }
        get("/all-investors") {
            val allInvestors = allInvestorsUseCase.invoke()
            if (allInvestors.isEmpty()) {
                call.respond(status = HttpStatusCode.BadRequest, "Sorry")
            } else {
                call.respond(status = HttpStatusCode.OK, allInvestors.asJson())
            }
        }
    }
}