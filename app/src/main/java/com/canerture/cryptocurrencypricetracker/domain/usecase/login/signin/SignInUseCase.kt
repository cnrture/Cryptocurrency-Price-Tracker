package com.canerture.cryptocurrencypricetracker.domain.usecase.login.signin

import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(email: String, password: String) =
        authenticator.signInWithEmailAndPassword(email, password)
}