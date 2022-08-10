package com.canerture.cryptocurrencypricetracker.domain.usecase.main

import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class GetFirebaseUserUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.getCurrentUser()
}