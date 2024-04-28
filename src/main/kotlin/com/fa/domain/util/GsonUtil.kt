package com.fa.domain.util

import com.google.gson.Gson

object GsonUtil {
    fun Any.asJson(): String = Gson().toJson(this, this::class.java)
    fun <T> String.jsonToModel(type: Class<T>): T? = Gson().fromJson(this, type)
}