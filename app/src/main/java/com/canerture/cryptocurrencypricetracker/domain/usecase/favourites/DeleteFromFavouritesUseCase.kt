package com.canerture.cryptocurrencypricetracker.domain.usecase.favourites

import com.canerture.cryptocurrencypricetracker.domain.model.FavouritesUI
import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import javax.inject.Inject

class DeleteFromFavouritesUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(favouritesUI: FavouritesUI) =
        authenticator.deleteFromFavourites(favouritesUI)
}