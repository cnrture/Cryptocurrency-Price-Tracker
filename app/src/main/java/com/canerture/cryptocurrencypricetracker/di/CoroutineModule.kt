package com.canerture.cryptocurrencypricetracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @Singleton
    @Named("IO")
    fun provideCoContextIO(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    @Named("Default")
    fun provideCoContextDefault(): CoroutineDispatcher = Dispatchers.Default
}