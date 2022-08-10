package com.canerture.cryptocurrencypricetracker.data.source.remote

import com.canerture.cryptocurrencypricetracker.common.Constants.COIN_BY_ID
import com.canerture.cryptocurrencypricetracker.common.Constants.COIN_LIST
import com.canerture.cryptocurrencypricetracker.common.Constants.COIN_MARKETS
import com.canerture.cryptocurrencypricetracker.data.model.coindetail.response.CoinDetailResponse
import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListResponse
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET(COIN_LIST)
    suspend fun getCoinList(): List<CoinListResponse>

    @GET(COIN_MARKETS)
    suspend fun getCoinMarkets(): List<CoinMarketsResponse>

    @GET(COIN_BY_ID)
    suspend fun getCoinById(@Path("id") coinId: String): CoinDetailResponse
}