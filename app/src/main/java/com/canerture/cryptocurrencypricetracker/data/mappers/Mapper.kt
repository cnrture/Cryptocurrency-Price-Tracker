package com.canerture.cryptocurrencypricetracker.data.mappers

import com.canerture.cryptocurrencypricetracker.common.Constants
import com.canerture.cryptocurrencypricetracker.data.model.coindetail.response.CoinDetailResponse
import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListEntity
import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListResponse
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsEntity
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsResponse
import com.canerture.cryptocurrencypricetracker.domain.model.CoinDetailUI
import com.canerture.cryptocurrencypricetracker.domain.model.CoinListUI
import com.canerture.cryptocurrencypricetracker.domain.model.CoinMarketsUI
import com.canerture.cryptocurrencypricetracker.domain.model.FavouritesUI

fun CoinDetailResponse.toCoinDetailUI() = CoinDetailUI(
    name = name ?: Constants.NA,
    coinId = id,
    hashingAlgorithm = hashingAlgorithm ?: Constants.NA,
    description = description?.en ?: Constants.NA,
    image = image?.large,
    currentPrice = marketData?.currentPrice?.usd,
    priceChangePercentage24h = marketData?.priceChangePercentage24h
)

fun List<CoinListEntity>.toCoinListUI() = map {
    CoinListUI(
        id = it.id,
        coinId = it.coinId,
        name = it.name
    )
}

fun List<CoinListResponse>.toCoinListEntity() = map {
    CoinListEntity(
        coinId = it.id,
        name = it.name
    )
}

fun List<CoinMarketsEntity>.toCoinMarketsUI() = map {
    CoinMarketsUI(
        id = it.id,
        coinId = it.coinId,
        currentPrice = it.currentPrice,
        image = it.image,
        name = it.name,
        priceChangePercentage24h = it.priceChangePercentage24h
    )
}

fun List<CoinMarketsResponse>.toCoinMarketsEntity() = map {
    CoinMarketsEntity(
        coinId = it.id,
        ath = it.ath,
        athChangePercentage = it.athChangePercentage,
        athDate = it.athDate,
        atl = it.atl,
        atlChangePercentage = it.atlChangePercentage,
        atlDate = it.atlDate,
        circulatingSupply = it.circulatingSupply,
        currentPrice = it.currentPrice,
        fullyDilutedValuation = it.fullyDilutedValuation,
        high24h = it.high24h,
        image = it.image,
        lastUpdated = it.lastUpdated,
        low24h = it.low24h,
        marketCap = it.marketCap,
        marketCapChange24h = it.marketCapChange24h,
        marketCapChangePercentage24h = it.marketCapChangePercentage24h,
        marketCapRank = it.marketCapRank,
        maxSupply = it.maxSupply,
        name = it.name,
        priceChange24h = it.priceChange24h,
        priceChangePercentage24h = it.priceChangePercentage24h,
        symbol = it.symbol,
        totalSupply = it.totalSupply,
        totalVolume = it.totalVolume
    )
}

fun CoinDetailUI.toFavouriteUI() = FavouritesUI(
    name = name ?: "",
    coinId = coinId ?: "",
    hashingAlgorithm = hashingAlgorithm ?: "",
    description = description ?: "",
    image = image ?: "",
    currentPrice = currentPrice ?: 0.0,
    priceChangePercentage24h = priceChangePercentage24h ?: 0.0
)