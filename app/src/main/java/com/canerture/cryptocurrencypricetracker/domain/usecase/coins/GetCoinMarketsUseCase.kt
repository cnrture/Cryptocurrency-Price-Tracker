package com.canerture.cryptocurrencypricetracker.domain.usecase.coins

import com.canerture.cryptocurrencypricetracker.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinMarketsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke() = coinRepository.coinsMarkets()
}