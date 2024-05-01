package com.fa.domain.mappers

import com.fa.data.models.InvestorDTO
import com.fa.domain.models.Investor
import com.fa.domain.util.ModelMapper
import org.bson.types.ObjectId

class InvestorMapper : ModelMapper<InvestorDTO, Investor> {
    override fun Investor.toDto(): InvestorDTO =
        InvestorDTO(id = ObjectId(id), fullName = fullName, companies = companies, age = age, netWorth = netWorth)
    override fun InvestorDTO.toModel(): Investor =
        Investor(id = id.toString(), fullName = fullName, companies = companies, age = age, netWorth = netWorth)
}