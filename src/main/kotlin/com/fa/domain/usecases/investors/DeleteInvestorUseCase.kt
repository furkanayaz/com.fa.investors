package com.fa.domain.usecases.investors

import com.fa.data.services.investor.impl.InvestorService
import org.bson.types.ObjectId

class DeleteInvestorUseCase(private val investorService: InvestorService) {
    suspend operator fun invoke(id: ObjectId) = investorService.deleteInvestor(id = id)
}