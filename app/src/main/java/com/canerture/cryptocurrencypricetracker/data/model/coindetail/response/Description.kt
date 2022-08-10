package com.canerture.cryptocurrencypricetracker.data.model.coindetail.response


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String?
)