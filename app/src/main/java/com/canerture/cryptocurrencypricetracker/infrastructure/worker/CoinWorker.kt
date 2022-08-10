package com.canerture.cryptocurrencypricetracker.infrastructure.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.canerture.cryptocurrencypricetracker.common.Constants.DESCRIPTION
import com.canerture.cryptocurrencypricetracker.common.Constants.TITLE
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import com.canerture.cryptocurrencypricetracker.domain.source.remote.RemoteDataSource
import com.canerture.cryptocurrencypricetracker.infrastructure.notification.NotificationUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CoinWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val authenticator: Authenticator,
    private val remoteDataSource: RemoteDataSource
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {

            var equalState = true

            val coinsMarkets = remoteDataSource.getCoinMarkets()

            authenticator.getFavourites().collect { result ->
                when (result) {
                    is Resource.Success -> {

                        if (result.data.isNotEmpty()) {
                            result.data.forEach { favourite ->
                                coinsMarkets.forEach { coinMarkets ->
                                    if (favourite.name == coinMarkets.name && favourite.currentPrice != coinMarkets.currentPrice) {
                                        equalState = !equalState
                                    }
                                }
                            }

                            if (equalState) NotificationUtils.showNotification(
                                applicationContext,
                                TITLE,
                                DESCRIPTION
                            )
                        }
                    }
                    else -> {}
                }
            }

            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }
}