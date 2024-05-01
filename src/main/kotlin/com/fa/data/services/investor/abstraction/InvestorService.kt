package com.fa.data.services.investor.abstraction

import com.fa.data.models.InvestorDTO
import com.fa.domain.models.Investor
import org.bson.types.ObjectId

interface InvestorService {
    val id: String
    suspend fun allInvestors(): List<Investor>
    suspend fun getInvestorById(id: String): Investor?
    suspend fun insertInvestor(investorDTO: InvestorDTO): Boolean
    suspend fun putInvestor(id: String, investor: InvestorDTO): Long
    suspend fun deleteInvestor(id: ObjectId): Boolean
}