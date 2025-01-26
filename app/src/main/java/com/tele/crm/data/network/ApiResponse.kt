package com.tele.crm.data.network

data class ApiResponse<T>(
    val message: String,
    val data: T
)
