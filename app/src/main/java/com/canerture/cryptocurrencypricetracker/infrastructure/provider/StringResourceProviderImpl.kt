package com.canerture.cryptocurrencypricetracker.infrastructure.provider

import android.content.Context
import androidx.annotation.StringRes
import com.canerture.cryptocurrencypricetracker.domain.provider.StringResourceProvider
import javax.inject.Inject

class StringResourceProviderImpl @Inject constructor(
    private val context: Context
) : StringResourceProvider {

    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}