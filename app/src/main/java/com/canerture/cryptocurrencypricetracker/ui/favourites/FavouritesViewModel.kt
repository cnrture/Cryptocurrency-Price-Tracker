package com.canerture.cryptocurrencypricetracker.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.cryptocurrencypricetracker.common.DetailUIEffect
import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.model.FavouritesUI
import com.canerture.cryptocurrencypricetracker.domain.usecase.favourites.DeleteFromFavouritesUseCase
import com.canerture.cryptocurrencypricetracker.domain.usecase.favourites.GetFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val deleteFromFavouritesUseCase: DeleteFromFavouritesUseCase
) : ViewModel() {

    private val _deleteFromFavouritesFlow = MutableSharedFlow<DetailUIEffect>()
    val deleteFromFavouritesFlow = _deleteFromFavouritesFlow.asSharedFlow()

    private val _favouritesFlow = MutableStateFlow<Resource<List<FavouritesUI>>>(Resource.Loading)
    val favouritesFlow = _favouritesFlow.asStateFlow()

    init {
        getFavourites()
    }

    private fun getFavourites() = viewModelScope.launch {
        getFavouritesUseCase.invoke().collect { result ->
            _favouritesFlow.emit(result)
        }
    }

    fun deleteFromFavourites(favouritesUI: FavouritesUI) = viewModelScope.launch {
        deleteFromFavouritesUseCase.invoke(favouritesUI).collect { result ->
            when (result) {
                Resource.Loading -> {}
                is Resource.Success -> getFavourites()
                is Resource.Error -> _deleteFromFavouritesFlow.emit(
                    DetailUIEffect.SnackBar(result.throwable.message.toString())
                )
            }
        }
    }
}