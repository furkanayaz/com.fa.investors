package com.fa.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDTO(
    val name: String,
    val employeeCount: Int,
    val foundedDate: String
)
