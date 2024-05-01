package com.fa.domain.usecases.investors

import com.fa.data.models.InvestorDTO
import com.fa.data.services.investor.abstraction.InvestorService

class InsertInvestorUseCase(private val investorService: InvestorService) {
    suspend operator fun invoke(investorDTO: InvestorDTO) = investorService.insertInvestor(investorDTO = investorDTO)
}