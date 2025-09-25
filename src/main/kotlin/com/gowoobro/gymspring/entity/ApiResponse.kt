package com.gowoobro.gymspring.entity

data class ApiResponse<T>(
    val _t: Long = System.currentTimeMillis(),
    val code: String = "ok",
    val items: T
)

data class ApiSingleResponse<T>(
    val _t: Long = System.currentTimeMillis(),
    val code: String = "ok",
    val item: T
)