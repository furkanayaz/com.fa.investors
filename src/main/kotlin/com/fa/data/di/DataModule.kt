package com.fa.data.di

import com.fa.data.consts.DBConst.COMPANY_COLLECTION
import com.fa.data.consts.DBConst.DATABASE_NAME
import com.fa.data.consts.DBConst.DB_CONNECTION_URL
import com.fa.data.consts.DBConst.INVESTOR_COLLECTION
import com.fa.data.consts.DBConst.INVESTOR_SERVICE
import com.fa.data.models.Company
import com.fa.data.models.Investor
import com.fa.data.models.codecs.InvestorCodec
import com.fa.data.services.investor.abstraction.InvestorServiceImpl
import com.fa.data.services.investor.impl.InvestorService
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.bson.codecs.IntegerCodec
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single {
        MongoClients.create(DB_CONNECTION_URL)
    }

    single {
        val codecRegistry = CodecRegistries.fromRegistries(
            CodecRegistries.fromCodecs(IntegerCodec(), InvestorCodec()),
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().register(InvestorCodec::class.java).build())
        )

        get<MongoClient>().getDatabase(DATABASE_NAME).withCodecRegistry(codecRegistry)
    }

    single(named(INVESTOR_COLLECTION)) {
        get<MongoDatabase>().getCollection(INVESTOR_COLLECTION, Investor::class.java)
    }

    single(named(COMPANY_COLLECTION)) {
        get<MongoDatabase>().getCollection(COMPANY_COLLECTION, Company::class.java)
    }

    single<InvestorService>(named(INVESTOR_SERVICE)) { InvestorServiceImpl(db = get(named(INVESTOR_COLLECTION))) }
}