package com.fa.domain.util

/**
 * [T] is a DTO class.
 * [R] is a Converted Model Class.
 * */

interface ModelMapper<T: Any, R: Any> {
    fun R.toDto(): T
    fun T.toModel(): R
}