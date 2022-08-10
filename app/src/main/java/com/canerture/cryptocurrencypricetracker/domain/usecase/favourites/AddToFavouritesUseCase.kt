package com.canerture.cryptocurrencypricetracker.domain.usecase.favourites

import com.canerture.cryptocurrencypricetracker.domain.model.CoinDetailUI
import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(coinDetailUI: CoinDetailUI) = authenticator.addToFavourites(coinDetailUI)
}