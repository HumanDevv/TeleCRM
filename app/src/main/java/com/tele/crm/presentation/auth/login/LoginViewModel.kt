package com.tele.crm.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tele.crm.data.network.ApiResponse
import com.tele.crm.data.network.AppState
import com.tele.crm.data.network.errorHandel.BaseError
import com.tele.crm.data.network.model.auth.login.LoginRequest
import com.tele.crm.data.network.model.auth.login.LoginResponse
import com.tele.crm.data.network.repository.CRMRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: CRMRepositoryImpl
) : ViewModel() {
    private val _loginResult = MutableLiveData<AppState>()
    val loginResult: LiveData<AppState> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginResult.postValue(AppState.Loading)
            val loginRequest = LoginRequest(email, password)
            val response = repository.login(loginRequest)
            handleLoginResponse(response)
        }
    }

    private fun handleLoginResponse(response: ApiResponse<LoginResponse>) {
        when (response) {
            is ApiResponse.Success -> {
                val loginState = if (response.data.message == "001") {
                    AppState.LoginSuccess(response.data)
                } else {
                    AppState.LoginSuccess(response.data)
                }
                _loginResult.postValue(loginState)
            }
            is ApiResponse.Error -> {
                val errorState = when (response.error) {
                    is BaseError.UnknownError -> AppState.UnknownError
                    is BaseError.NetworkError -> AppState.NoInternetConnection
                    is BaseError.ServerError -> response.error.responseBody?.let {
                        AppState.ServerError(it)
                    }
                    else -> null
                }
                errorState?.let { _loginResult.postValue(it) }
            }

            else -> {}
        }
    }


}