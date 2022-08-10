package com.canerture.cryptocurrencypricetracker.di

import com.canerture.cryptocurrencypricetracker.common.Constants.BASE_URL
import com.canerture.cryptocurrencypricetracker.data.source.remote.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideCoinService(): CoinService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoinService::class.java)
}