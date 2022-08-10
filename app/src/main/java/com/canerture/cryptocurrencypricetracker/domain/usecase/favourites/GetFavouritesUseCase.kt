package com.canerture.cryptocurrencypricetracker.domain.usecase.favourites

import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.getFavourites()
}