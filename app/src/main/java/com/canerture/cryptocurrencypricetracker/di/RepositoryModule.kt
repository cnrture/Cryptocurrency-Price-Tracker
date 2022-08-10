package com.canerture.cryptocurrencypricetracker.di

import com.canerture.cryptocurrencypricetracker.data.repository.CoinRepositoryImpl
import com.canerture.cryptocurrencypricetracker.data.source.local.CoinsRoomDB
import com.canerture.cryptocurrencypricetracker.domain.repository.CoinRepository
import com.canerture.cryptocurrencypricetracker.domain.source.local.LocalDataSource
import com.canerture.cryptocurrencypricetracker.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        coinsRoomDB: CoinsRoomDB,
        @Named("Default") coContextDefault: CoroutineDispatcher
    ): CoinRepository =
        CoinRepositoryImpl(remoteDataSource, localDataSource, coinsRoomDB, coContextDefault)
}