package com.canerture.cryptocurrencypricetracker.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.model.CoinListUI
import com.canerture.cryptocurrencypricetracker.domain.usecase.coins.GetCoinListUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.coins.SearchCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCoinViewModel @Inject constructor(
    getCoinListUseCase: GetCoinListUseCase,
    private val searchCoinUseCase: SearchCoinUseCase,
) : ViewModel() {

    private val _coinListFlow = MutableStateFlow<Resource<List<CoinListUI>>>(Resource.Loading)
    val coinListFlow = _coinListFlow.asStateFlow()

    val coinList = getCoinListUseCase.invoke()

    fun searchCoin(searchQuery: String) = viewModelScope.launch {
        searchCoinUseCase.invoke(searchQuery).collect {
            _coinListFlow.emit(it)
        }
    }
}