package com.canerture.cryptocurrencypricetracker.domain.provider

interface StringResourceProvider {
    fun getString(stringResId: Int): String
}