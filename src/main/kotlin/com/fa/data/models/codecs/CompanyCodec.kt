package com.fa.data.models.codecs

import com.fa.data.models.Company
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class CompanyCodec: Codec<Company> {
    override fun encode(p0: BsonWriter, p1: Company, p2: EncoderContext) {
        p0.writeStartDocument()
        p0.writeString("id", p1.id)
        p0.writeString("name", p1.name)
        p0.writeInt32("employeeCount", p1.employeeCount)
        p0.writeString("foundedDate", p1.foundedDate)
        p0.writeEndDocument()
    }

    override fun getEncoderClass(): Class<Company> = Company::class.java

    override fun decode(p0: BsonReader, p1: DecoderContext): Company {
        p0.readStartDocument()
        val id = p0.readString("id")
        val name = p0.readString("name")
        val employeeCount = p0.readInt32("employeeCount")
        val foundedDate = p0.readString("foundedDate")
        p0.readEndDocument()
        return Company(id = id, name = name, employeeCount = employeeCount, foundedDate = foundedDate)
    }
}