package com.canerture.cryptocurrencypricetracker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.model.CoinMarketsUI
import com.canerture.cryptocurrencypricetracker.domain.usecase.coins.GetCoinMarketsUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.login.signout.SignOutUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.main.GetFirebaseUserUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.worker.WorkerOnSuccessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val getCoinMarketsUseCase: GetCoinMarketsUseCase,
    private val signOutUseCase: SignOutUseCase,
    workerOnSuccessUseCase: WorkerOnSuccessUseCase
) : ViewModel() {

    private val _coinMarketsFlow = MutableStateFlow<Resource<List<CoinMarketsUI>>>(Resource.Loading)
    val coinMarketsFlow = _coinMarketsFlow.asStateFlow()

    init {
        coinMarkets()
    }

    val workInfo = workerOnSuccessUseCase.invoke()

    val currentUser = getFirebaseUserUseCase.invoke()

    fun signOut() = signOutUseCase.invoke()

    fun coinMarkets() = viewModelScope.launch {
        getCoinMarketsUseCase.invoke().collect { result ->
            _coinMarketsFlow.emit(result)
        }
    }
}