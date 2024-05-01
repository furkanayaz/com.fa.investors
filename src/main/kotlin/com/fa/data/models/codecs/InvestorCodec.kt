package com.fa.data.models.codecs

import com.fa.data.models.CompanyDTO
import com.fa.data.models.InvestorDTO
import org.bson.BsonReader
import org.bson.BsonType
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class InvestorCodec : Codec<InvestorDTO> {
    override fun encode(p0: BsonWriter, p1: InvestorDTO, p2: EncoderContext) {
        p0.writeStartDocument()
        p0.writeObjectId("_id", p1.id)
        p0.writeString("fullName", p1.fullName)
        p0.writeName("companies")
        p0.writeStartArray()
        for (company in p1.companies) {
            p0.writeStartDocument()
            p0.writeString("name", company.name)
            p0.writeInt32("employeeCount", company.employeeCount)
            p0.writeString("foundedDate", company.foundedDate)
            p0.writeEndDocument()
        }
        p0.writeEndArray()
        p0.writeInt32("age", p1.age)
        p0.writeInt32("netWorth", p1.netWorth)
        p0.writeEndDocument()
    }

    override fun getEncoderClass(): Class<InvestorDTO> = InvestorDTO::class.java

    override fun decode(p0: BsonReader, p1: DecoderContext): InvestorDTO {
        p0.readStartDocument()
        val id = p0.readObjectId("_id")
        val fullName = p0.readString("fullName")
        p0.readName("companies")
        p0.readStartArray()

        val companies = mutableListOf<CompanyDTO>()

        while (p0.readBsonType() != BsonType.END_OF_DOCUMENT) {
            p0.readStartDocument()
            val name = p0.readString("name")
            val employeeCount = p0.readInt32("employeeCount")
            val foundedDate = p0.readString("foundedDate")

            companies.add(CompanyDTO(name = name, employeeCount = employeeCount, foundedDate = foundedDate))

            p0.readEndDocument()
        }

        p0.readEndArray()

        val age = p0.readInt32("age")
        val netWorth = p0.readInt32("netWorth")
        p0.readEndDocument()

        return InvestorDTO(
            id = id, fullName = fullName, companies = companies, age = age, netWorth = netWorth
        )
    }
}