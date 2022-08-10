package com.canerture.cryptocurrencypricetracker.domain.usecase.worker

import com.canerture.cryptocurrencypricetracker.domain.provider.WorkerProvider
import javax.inject.Inject

class CreateWorkUseCase @Inject constructor(
    private val workerProvider: WorkerProvider
) {
    operator fun invoke() = workerProvider.createWork()
}