package com.canerture.cryptocurrencypricetracker.common

sealed class DetailUIEffect {
    data class SnackBar(val message: String) : DetailUIEffect()
}