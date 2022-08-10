package com.canerture.cryptocurrencypricetracker.domain.usecase.login.signup

import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class IsCurrentUserExistUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.isCurrentUserExist()
}