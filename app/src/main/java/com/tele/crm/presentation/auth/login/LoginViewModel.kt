package com.tele.crm.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tele.crm.data.network.ApiResponse
import com.tele.crm.data.network.model.auth.login.LoginRequest
import com.tele.crm.data.network.model.auth.login.LoginResponse
import com.tele.crm.data.network.repository.CRMRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: CRMRepositoryImpl
) : ViewModel() {

    private val _loginResponse = MutableStateFlow<ApiResponse<LoginResponse>?>(null)
    val loginResponse: StateFlow<ApiResponse<LoginResponse>?> = _loginResponse

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val request = LoginRequest(email_id = email, password = password)
            val result = repository.login(request)
            _loginResponse.value = result.getOrNull()
        }
    }
}