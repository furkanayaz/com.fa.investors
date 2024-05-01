package com.fa.domain.models

import com.fa.data.models.CompanyDTO

data class Investor(
    val id: String? = null,
    val fullName: String,
    val companies: List<CompanyDTO> = emptyList(),
    val age: Int,
    val netWorth: Int
)