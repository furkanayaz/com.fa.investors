package com.fa.domain.usecases.investors

import com.fa.data.services.investor.abstraction.InvestorService
import org.bson.types.ObjectId

class GetInvestorByIdUseCase(private val investorService: InvestorService) {
    suspend operator fun invoke(id: String) = investorService.getInvestorById(id = id)
}