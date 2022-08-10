package com.canerture.cryptocurrencypricetracker.domain.usecase.coins

import com.canerture.cryptocurrencypricetracker.domain.repository.CoinRepository
import javax.inject.Inject
import kotlin.time.Duration

class GetCurrentPriceByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(period: Duration, coinId: String) =
        coinRepository.currentPriceById(period, coinId)
}