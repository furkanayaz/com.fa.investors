package com.fa.domain.usecases.investors

import com.fa.data.models.InvestorDTO
import com.fa.data.services.investor.abstraction.InvestorService

class PutInvestorUseCase(private val investorService: InvestorService) {
    suspend operator fun invoke(id: String, investor: InvestorDTO): Long = investorService.putInvestor(id = id, investor = investor)
}