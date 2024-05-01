package com.fa.domain.di

import com.fa.data.consts.DBConst.INVESTOR_SERVICE
import com.fa.domain.mappers.InvestorMapper
import com.fa.domain.usecases.investors.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    single { AllInvestorsUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { GetInvestorByIdUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { InsertInvestorUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { PutInvestorUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { DeleteInvestorUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single {
        InvestorMapper()
    }
}