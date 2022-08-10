package com.canerture.cryptocurrencypricetracker.domain.usecase.coins

import com.canerture.cryptocurrencypricetracker.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String) = coinRepository.coinById(coinId)
}