package com.fa.domain.usecases.investors

import com.fa.data.models.Investor
import com.fa.data.services.investor.impl.InvestorService

class InsertInvestorUseCase(private val investorService: InvestorService) {
    suspend operator fun invoke(investor: Investor) = investorService.insertInvestor(investor = investor)
}