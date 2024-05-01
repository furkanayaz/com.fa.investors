package com.fa.data.services.investor.impl

import com.fa.data.models.InvestorDTO
import com.fa.data.services.investor.abstraction.InvestorService
import com.fa.domain.mappers.InvestorMapper
import com.fa.domain.models.Investor
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import org.bson.types.ObjectId

class InvestorServiceImpl(private val db: MongoCollection<InvestorDTO>, private val mapper: InvestorMapper) :
    InvestorService {
    override val id: String
        get() = "_id"

    override suspend fun allInvestors(): List<Investor> {
        val result = db.find().toList()
        return with(mapper) {
            result.map {
                it.toModel()
            }
        }
    }

    override suspend fun getInvestorById(id: String): Investor? {
        val result = db.find(Filters.eq(this.id, ObjectId(id))).first()
        return with(mapper) {
            result?.toModel()
        }
    }

    override suspend fun insertInvestor(investorDTO: InvestorDTO): Boolean = db.insertOne(investorDTO).wasAcknowledged()

    override suspend fun putInvestor(id: String, investor: InvestorDTO): Long {
        val result = db.updateOne(Filters.eq(this.id, ObjectId(id)), Updates.set(investor::fullName.name, investor.fullName), UpdateOptions().upsert(false))

        return result.modifiedCount
    }

    override suspend fun deleteInvestor(id: ObjectId): Boolean = db.deleteOne(Filters.eq(this.id, id)).wasAcknowledged()
}