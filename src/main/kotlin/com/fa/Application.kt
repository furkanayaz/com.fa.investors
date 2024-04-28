package com.fa

import com.fa.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureKoin()
    configureHTTP()
    configureAdministration()
    configureSerialization()
    //configureDatabases()
    configureMonitoring()
    configureContentNegotiation()
    configureRouting()
}