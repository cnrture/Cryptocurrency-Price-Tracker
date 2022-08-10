package com.canerture.cryptocurrencypricetracker.domain.source.remote

import com.canerture.cryptocurrencypricetracker.data.model.coindetail.response.CoinDetailResponse
import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListResponse
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsResponse

interface RemoteDataSource {

    suspend fun getCoinList(): List<CoinListResponse>

    suspend fun getCoinMarkets(): List<CoinMarketsResponse>

    suspend fun getCoinById(coinId: String): CoinDetailResponse

}