package com.canerture.cryptocurrencypricetracker.data.source.remote

import com.canerture.cryptocurrencypricetracker.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val coinService: CoinService
) : RemoteDataSource {

    override suspend fun getCoinList() = coinService.getCoinList()

    override suspend fun getCoinMarkets() = coinService.getCoinMarkets()

    override suspend fun getCoinById(coinId: String) = coinService.getCoinById(coinId)

}