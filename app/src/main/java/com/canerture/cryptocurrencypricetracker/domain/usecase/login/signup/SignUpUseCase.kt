package com.canerture.cryptocurrencypricetracker.domain.usecase.login.signup

import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(email: String, password: String) =
        authenticator.signUpWithEmailAndPassword(email, password)
}