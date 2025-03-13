package dev.belalkhan.netrosample.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.belalkhan.netrosample.auth.AuthApiService
import dev.belalkhan.netrosample.auth.models.LoginRequest
import dev.belalkhan.netrosample.auth.models.LoginResponse
import dev.belalkhan.netrosample.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
    @Inject
    constructor(
        private val authApiService: AuthApiService,
    ) : ViewModel() {
        private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
        val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

        private val _navigationEvent = MutableSharedFlow<String>()
        val navigationEvent: SharedFlow<String> = _navigationEvent.asSharedFlow()

        fun authenticate(
            username: String,
            password: String,
        ) {
            viewModelScope.launch {
                _loginState.value = LoginState.Loading
                try {
                    val result = authApiService.loginUser(LoginRequest(username, password))
                    _loginState.value = LoginState.Success(result)
                    _navigationEvent.emit(Screen.Users.route)
                } catch (e: Exception) {
                    _loginState.value = LoginState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

// Sealed class for login state
sealed class LoginState {
    object Idle : LoginState()

    object Loading : LoginState()

    data class Success(val response: LoginResponse) : LoginState()

    data class Error(val message: String) : LoginState()
}
