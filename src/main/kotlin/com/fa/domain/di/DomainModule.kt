package com.fa.domain.di

import com.fa.data.consts.DBConst.INVESTOR_SERVICE
import com.fa.data.di.dataModule
import com.fa.domain.usecases.investors.AllInvestorsUseCase
import com.fa.domain.usecases.investors.DeleteInvestorUseCase
import com.fa.domain.usecases.investors.GetInvestorByIdUseCase
import com.fa.domain.usecases.investors.InsertInvestorUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    includes(dataModule)

    single { AllInvestorsUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { GetInvestorByIdUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { InsertInvestorUseCase(investorService = get(named(INVESTOR_SERVICE))) }

    single { DeleteInvestorUseCase(investorService = get(named(INVESTOR_SERVICE))) }
}