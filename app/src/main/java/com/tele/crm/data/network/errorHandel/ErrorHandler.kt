package com.tele.crm.data.network.errorHandel

import com.tele.crm.data.network.errorHandel.BaseError

interface ErrorHandler {
    fun getErrorType(throwable: Throwable): BaseError
}