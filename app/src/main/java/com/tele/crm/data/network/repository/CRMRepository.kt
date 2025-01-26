package com.tele.crm.data.network.repository

import retrofit2.Response

interface CRMRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T>

}