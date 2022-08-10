package com.canerture.cryptocurrencypricetracker.data.model.coindetail.response


import com.google.gson.annotations.SerializedName

data class ReposUrl(
    @SerializedName("bitbucket")
    val bitbucket: List<Any?>?,
    @SerializedName("github")
    val github: List<String?>?
)