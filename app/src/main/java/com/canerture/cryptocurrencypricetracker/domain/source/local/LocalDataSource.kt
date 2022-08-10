package com.canerture.cryptocurrencypricetracker.domain.source.local

import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListEntity
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsEntity

interface LocalDataSource {

    suspend fun insertCoinList(items: List<CoinListEntity>)

    suspend fun insertCoinMarketsList(items: List<CoinMarketsEntity>)

    suspend fun getCoinList(): List<CoinListEntity>

    suspend fun getCoinMarkets(): List<CoinMarketsEntity>

    suspend fun searchCoin(searchQuery: String): List<CoinListEntity>

    suspend fun deleteCoinList()

    suspend fun deleteCoinMarkets()
}