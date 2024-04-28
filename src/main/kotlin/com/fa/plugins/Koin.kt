package com.fa.plugins

import com.fa.data.di.dataModule
import com.fa.domain.di.domainModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(dataModule, domainModule)
    }
}