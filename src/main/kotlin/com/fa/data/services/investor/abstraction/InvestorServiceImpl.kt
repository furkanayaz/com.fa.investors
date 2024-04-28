package com.fa.data.services.investor.abstraction

import com.fa.data.models.Investor
import com.fa.data.services.investor.impl.InvestorService
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.bson.types.ObjectId

class InvestorServiceImpl(private val db: MongoCollection<Investor>) : InvestorService {
    override val id: String
        get() = Investor::id.name

    override suspend fun allInvestors(): List<Investor> = db.find().toList()

    override suspend fun getInvestorById(id: ObjectId): Investor? = db.find(Filters.eq(this.id, id)).first()

    override suspend fun insertInvestor(investor: Investor): Boolean = db.insertOne(investor).wasAcknowledged()

    override suspend fun deleteInvestor(id: ObjectId): Boolean = db.deleteOne(Filters.eq(this.id, id)).wasAcknowledged()
}