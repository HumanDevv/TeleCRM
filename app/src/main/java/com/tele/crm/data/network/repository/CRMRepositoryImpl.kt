package com.tele.crm.data.network.repository

import com.tele.crm.data.network.ApiResponse
import com.tele.crm.data.network.ApiService
import com.tele.crm.data.network.model.auth.login.LoginRequest
import com.tele.crm.data.network.model.auth.login.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CRMRepositoryImpl @Inject constructor(  private val apiService: ApiService
) : CRMRepository {

    override suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("Response body is null"))
                } else {
                    Result.failure(Exception("API Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun login(loginRequest: LoginRequest): Result<ApiResponse<LoginResponse>> {
        return safeApiCall {
            apiService.login(loginRequest)
        }
    }
}