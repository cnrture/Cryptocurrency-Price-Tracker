package com.canerture.cryptocurrencypricetracker.domain.model

data class CoinMarketsUI(
    val id: Int?,
    val coinId: String?,
    val currentPrice: Double?,
    val image: String?,
    val name: String?,
    val priceChangePercentage24h: Double?,
)