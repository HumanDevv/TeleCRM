package com.tele.crm.data.network

import com.tele.crm.data.network.errorHandel.BaseError

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class SuccessL<T>(val data: ResponseModel<T>) : ApiResponse<ResponseModel<T>>()
    data class SuccessR<T>(val data: ResponseModel<ArrayList<T>>) :
        ApiResponse<ResponseModel<ArrayList<T>>>()

    data class Error<T>(val error: BaseError) : ApiResponse<T>()
    data class ErrorL<T>(val error: BaseError) : ApiResponse<ResponseModel<T>>()
    data class ErrorR<T>(val error: BaseError) : ApiResponse<ResponseModel<ArrayList<T>>>()
}