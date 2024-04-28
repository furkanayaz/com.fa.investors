package com.fa.data.services.investor.impl

import com.fa.data.models.Investor
import org.bson.types.ObjectId

interface InvestorService {
    val id: String
    suspend fun allInvestors(): List<Investor>
    suspend fun getInvestorById(id: ObjectId): Investor?
    suspend fun insertInvestor(investor: Investor): Boolean
    suspend fun deleteInvestor(id: ObjectId): Boolean
}