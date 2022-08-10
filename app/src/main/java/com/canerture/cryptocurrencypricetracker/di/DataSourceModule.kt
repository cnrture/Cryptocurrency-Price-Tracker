package com.canerture.cryptocurrencypricetracker.di

import com.canerture.cryptocurrencypricetracker.data.source.local.CoinsDAO
import com.canerture.cryptocurrencypricetracker.data.source.local.RoomDataSourceImpl
import com.canerture.cryptocurrencypricetracker.data.source.remote.CoinService
import com.canerture.cryptocurrencypricetracker.data.source.remote.RemoteDataSourceImpl
import com.canerture.cryptocurrencypricetracker.domain.source.local.LocalDataSource
import com.canerture.cryptocurrencypricetracker.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        coinService: CoinService,
    ): RemoteDataSource =
        RemoteDataSourceImpl(coinService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        coinsDAO: CoinsDAO
    ): LocalDataSource = RoomDataSourceImpl(coinsDAO)
}