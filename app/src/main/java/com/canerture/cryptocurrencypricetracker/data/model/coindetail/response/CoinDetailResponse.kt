package com.canerture.cryptocurrencypricetracker.data.model.coindetail.response

import com.google.gson.annotations.SerializedName

data class CoinDetailResponse(
    @SerializedName("additional_notices")
    val additionalNotices: List<Any>?,
    @SerializedName("asset_platform_id")
    val assetPlatformId: Any?,
    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Int?,
    @SerializedName("categories")
    val categories: List<String>?,
    @SerializedName("coingecko_rank")
    val coingeckoRank: Int?,
    @SerializedName("coingecko_score")
    val coingeckoScore: Double?,
    @SerializedName("community_score")
    val communityScore: Double?,
    @SerializedName("country_origin")
    val countryOrigin: String?,
    @SerializedName("description")
    val description: Description?,
    @SerializedName("developer_score")
    val developerScore: Double?,
    @SerializedName("genesis_date")
    val genesisDate: String?,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("liquidity_score")
    val liquidityScore: Double?,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,
    @SerializedName("market_data")
    val marketData: MarketData?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("platforms")
    val platforms: Platforms?,
    @SerializedName("public_interest_score")
    val publicInterestScore: Double?,
    @SerializedName("public_interest_stats")
    val publicInterestStats: PublicInterestStats?,
    @SerializedName("public_notice")
    val publicNotice: Any?,
    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double?,
    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double?,
    @SerializedName("status_updates")
    val statusUpdates: List<Any>?,
    @SerializedName("symbol")
    val symbol: String?
)