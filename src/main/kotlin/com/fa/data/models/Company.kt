package com.fa.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Company(
    @BsonId val id: String = ObjectId.get().toString(),
    val name: String,
    val employeeCount: Int,
    val foundedDate: String
)
