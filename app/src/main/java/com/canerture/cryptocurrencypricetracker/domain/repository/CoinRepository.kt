package com.canerture.cryptocurrencypricetracker.domain.repository

import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.model.CoinDetailUI
import com.canerture.cryptocurrencypricetracker.domain.model.CoinListUI
import com.canerture.cryptocurrencypricetracker.domain.model.CoinMarketsUI
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface CoinRepository {

    fun coinsMarkets(): Flow<Resource<List<CoinMarketsUI>>>

    fun coinList(): Flow<Resource<List<CoinListUI>>>

    fun searchCoin(searchQuery: String): Flow<Resource<List<CoinListUI>>>

    fun coinById(coinId: String): Flow<Resource<CoinDetailUI>>

    fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>>

}