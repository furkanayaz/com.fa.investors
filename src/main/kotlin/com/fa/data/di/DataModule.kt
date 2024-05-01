package com.fa.data.di

import com.fa.data.consts.DBConst.COMPANY_COLLECTION
import com.fa.data.consts.DBConst.DATABASE_NAME
import com.fa.data.consts.DBConst.DB_CONNECTION_URL
import com.fa.data.consts.DBConst.INVESTOR_COLLECTION
import com.fa.data.consts.DBConst.INVESTOR_SERVICE
import com.fa.data.models.CompanyDTO
import com.fa.data.models.InvestorDTO
import com.fa.data.models.codecs.CompanyCodec
import com.fa.data.models.codecs.InvestorCodec
import com.fa.data.services.investor.impl.InvestorServiceImpl
import com.fa.data.services.investor.abstraction.InvestorService
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
            CodecRegistries.fromCodecs(IntegerCodec(), InvestorCodec(), CompanyCodec()),
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(
                PojoCodecProvider.builder().register(InvestorCodec::class.java, CompanyCodec::class.java).build()
            )
        )

        get<MongoClient>().getDatabase(DATABASE_NAME).withCodecRegistry(codecRegistry)
    }

    single(named(INVESTOR_COLLECTION)) {
        get<MongoDatabase>().getCollection(INVESTOR_COLLECTION, InvestorDTO::class.java)
    }

    single(named(COMPANY_COLLECTION)) {
        get<MongoDatabase>().getCollection(COMPANY_COLLECTION, CompanyDTO::class.java)
    }

    single<InvestorService>(named(INVESTOR_SERVICE)) { InvestorServiceImpl(db = get(named(INVESTOR_COLLECTION)), mapper = get()) }
}