package com.fa.plugins

import com.fa.domain.usecases.investors.*
import com.fa.plugins.routings.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val allInvestorsUseCase by inject<AllInvestorsUseCase>()
    val getInvestorByIdUseCase by inject<GetInvestorByIdUseCase>()
    val deleteInvestorUseCase by inject<DeleteInvestorUseCase>()
    val insertInvestorUseCase by inject<InsertInvestorUseCase>()
    val putInvestorUseCase by inject<PutInvestorUseCase>()

    routing {
        swaggerUI(path = "/api", swaggerFile = "openapi/documentation.yaml")
        allInvestors(useCase = allInvestorsUseCase)
        insertInvestor(useCase = insertInvestorUseCase)
        putInvestor(useCase = putInvestorUseCase)
        getInvestorById(useCase = getInvestorByIdUseCase)
        deleteInvestor(useCase = deleteInvestorUseCase)
    }
}