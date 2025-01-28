package com.tele.crm.data.network.repository

import com.tele.crm.data.network.ApiResponse
import com.tele.crm.data.network.ApiService
import com.tele.crm.data.network.errorHandel.ErrorHandler
import com.tele.crm.data.network.model.auth.login.LoginRequest
import com.tele.crm.data.network.model.auth.login.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CRMRepositoryImpl @Inject constructor(  private val apiService: ApiService,
                                              private val errorHandler: ErrorHandler
) : CRMRepository {
    override suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse> {
        return try {
            val result=apiService.login(loginRequest)
            ApiResponse.Success(result)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            ApiResponse.Error(errorType)
        }

    }


}