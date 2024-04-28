package com.fa.data.models.codecs

import com.fa.data.models.Investor
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class InvestorCodec: Codec<Investor> {
    override fun encode(p0: BsonWriter, p1: Investor, p2: EncoderContext) {
        p0.writeStartDocument()
        p0.writeObjectId("_id", p1.id)
        p0.writeString("fullName", p1.fullName)
        p0.writeInt32("age", p1.age)
        p0.writeInt32("netWorth", p1.netWorth)
        p0.writeEndDocument()
    }

    override fun getEncoderClass(): Class<Investor> = Investor::class.java

    override fun decode(p0: BsonReader, p1: DecoderContext): Investor {
        p0.readStartDocument()
        val id = p0.readObjectId("_id")
        val fullName = p0.readString("fullName")
        val age = p0.readInt32("age")
        val netWorth = p0.readInt32("netWorth")
        p0.readEndDocument()
        return Investor(id = id, fullName = fullName, age = age, netWorth = netWorth)
    }
}