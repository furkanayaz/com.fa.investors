package com.fa.data.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class Investor(
    @SerialName("_id")
    @Contextual val id: ObjectId?,
    val fullName: String,
    //val companies: List<Company>,
    val age: Int,
    val netWorth: Int
)
