package com.tele.crm.data.network.repository

import com.tele.crm.data.network.ApiResponse
import com.tele.crm.data.network.model.auth.login.LoginRequest
import com.tele.crm.data.network.model.auth.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CRMRepository {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>
}