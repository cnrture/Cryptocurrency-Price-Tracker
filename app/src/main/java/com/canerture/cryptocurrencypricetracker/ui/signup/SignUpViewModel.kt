package com.canerture.cryptocurrencypricetracker.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.usecase.login.signup.IsCurrentUserExistUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.login.signup.SignUpUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    isCurrentUserExistUseCase: IsCurrentUserExistUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpFlow = MutableSharedFlow<Resource<AuthResult>>()
    val signUpFlow = _signUpFlow.asSharedFlow()

    val isCurrentUserExist = isCurrentUserExistUseCase.invoke()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpUseCase.invoke(email, password).collect {
            _signUpFlow.emit(it)
        }
    }
}