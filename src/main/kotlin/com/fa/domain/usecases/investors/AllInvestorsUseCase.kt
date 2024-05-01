package com.fa.domain.usecases.investors

import com.fa.data.services.investor.abstraction.InvestorService

class AllInvestorsUseCase(private val investorService: InvestorService) {
    suspend operator fun invoke() = investorService.allInvestors()
}