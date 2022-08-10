package com.canerture.cryptocurrencypricetracker.infrastructure.worker

import android.content.Context
import androidx.work.*
import com.canerture.cryptocurrencypricetracker.common.Constants
import com.canerture.cryptocurrencypricetracker.common.Constants.SYNC_DATA
import com.canerture.cryptocurrencypricetracker.domain.provider.WorkerProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerProviderImpl @Inject constructor(context: Context) : WorkerProvider {

    private val workManager = WorkManager.getInstance(context)

    private val workConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()

    override fun createWork() {

        val workRequest = PeriodicWorkRequestBuilder<CoinWorker>(
            15, TimeUnit.MINUTES,
            15, TimeUnit.MINUTES
        ).setConstraints(workConstraints).setInitialDelay(15, TimeUnit.MINUTES).addTag(SYNC_DATA)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Constants.SYNC_DATA_WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    override fun onSuccess() = workManager.getWorkInfosByTagLiveData(SYNC_DATA)
}