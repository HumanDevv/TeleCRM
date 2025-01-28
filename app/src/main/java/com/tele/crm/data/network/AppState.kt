package com.tele.crm.data.network

import com.tele.crm.data.network.model.auth.login.LoginResponse


sealed class AppState {
    data object Loading: AppState()
    data object NoInternetConnection : AppState()
    data object UnknownError : AppState()

    data class ServerError(val message: String) : AppState()
    data class LoginSuccess(val login: LoginResponse) : AppState()


}