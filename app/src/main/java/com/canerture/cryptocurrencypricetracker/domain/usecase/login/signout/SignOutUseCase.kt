package com.canerture.cryptocurrencypricetracker.domain.usecase.login.signout

import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.signOut()
}