package com.canerture.cryptocurrencypricetracker.di

import android.app.Application
import com.canerture.cryptocurrencypricetracker.domain.provider.StringResourceProvider
import com.canerture.cryptocurrencypricetracker.domain.provider.WorkerProvider
import com.canerture.cryptocurrencypricetracker.infrastructure.provider.StringResourceProviderImpl
import com.canerture.cryptocurrencypricetracker.infrastructure.worker.WorkerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(application: Application): StringResourceProvider {
        return StringResourceProviderImpl(application)
    }

    @Provides
    @Singleton
    fun provideWorkerProvider(application: Application): WorkerProvider {
        return WorkerProviderImpl(application)
    }
}