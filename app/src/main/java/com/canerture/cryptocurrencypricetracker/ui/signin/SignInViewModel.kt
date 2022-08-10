package com.canerture.cryptocurrencypricetracker.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.usecase.login.signin.SignInUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInFlow = MutableSharedFlow<Resource<AuthResult>>()
    val signInFlow = _signInFlow.asSharedFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        signInUseCase.invoke(email, password).collect {
            _signInFlow.emit(it)
        }
    }
}