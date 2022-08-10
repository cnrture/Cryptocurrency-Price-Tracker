package com.canerture.cryptocurrencypricetracker.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.Constants.COIN_ID
import com.canerture.cryptocurrencypricetracker.common.DetailUIEffect
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.model.CoinDetailUI
import com.canerture.cryptocurrencypricetracker.domain.provider.StringResourceProvider
import com.canerture.cryptocurrencypricetracker.domain.usecase.coins.GetCoinByIdUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.coins.GetCurrentPriceByIdUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.favourites.AddToFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val currentPriceByIdUseCase: GetCurrentPriceByIdUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private var coinDetailUI: CoinDetailUI? = null

    private val _coinDetailFlow = MutableStateFlow<Resource<CoinDetailUI>>(Resource.Loading)
    val coinDetailFlow = _coinDetailFlow.asStateFlow()

    private val _currentPriceFlow = MutableStateFlow(0.0)
    val currentPriceFlow = _currentPriceFlow.asStateFlow()

    private val _addToFavourite = MutableSharedFlow<DetailUIEffect>()
    val addToFavourite = _addToFavourite.asSharedFlow()

    init {
        savedStateHandle.get<String>(COIN_ID)?.let {
            coinById(it)
        }
    }

    private fun coinById(coinId: String) = viewModelScope.launch {
        getCoinByIdUseCase.invoke(coinId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    coinDetailUI = result.data
                    _coinDetailFlow.emit(Resource.Success(result.data))
                }
                is Resource.Loading -> _coinDetailFlow.emit(Resource.Loading)
                is Resource.Error -> _coinDetailFlow.emit(Resource.Error(result.throwable))
            }
        }
    }

    fun currentPriceById(period: Duration) = viewModelScope.launch {
        savedStateHandle.get<String>(COIN_ID)?.let {
            currentPriceByIdUseCase.invoke(period, it).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Success -> _currentPriceFlow.emit(result.data)
                    is Resource.Error -> {}
                }
            }
        }
    }

    fun addToFavourites() = viewModelScope.launch {
        coinDetailUI?.let {
            addToFavouritesUseCase.invoke(it).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Success -> _addToFavourite.emit(
                        DetailUIEffect.SnackBar(
                            stringResourceProvider.getString(R.string.add_to_favourites_successful)
                        )
                    )
                    is Resource.Error -> _addToFavourite.emit(
                        DetailUIEffect.SnackBar(
                            result.throwable.message.toString()
                        )
                    )
                }
            }
        } ?: run {
            _addToFavourite.emit(
                DetailUIEffect.SnackBar(stringResourceProvider.getString(R.string.something_went_wrong))
            )
        }
    }
}