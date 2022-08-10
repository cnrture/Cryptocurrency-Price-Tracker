package com.canerture.cryptocurrencypricetracker.data.model.coinmarket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_markets")
data class CoinMarketsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "coinId")
    val coinId: String?,

    @ColumnInfo(name = "ath")
    val ath: Double?,

    @ColumnInfo(name = "priceType")
    val athChangePercentage: Double?,

    @ColumnInfo(name = "date")
    val athDate: String?,

    @ColumnInfo(name = "atl")
    val atl: Double?,

    @ColumnInfo(name = "atlChangePercentage")
    val atlChangePercentage: Double?,

    @ColumnInfo(name = "atlDate")
    val atlDate: String?,

    @ColumnInfo(name = "circulatingSupply")
    val circulatingSupply: Double?,

    @ColumnInfo(name = "currentPrice")
    val currentPrice: Double?,

    @ColumnInfo(name = "fullyDilutedValuation")
    val fullyDilutedValuation: Long?,

    @ColumnInfo(name = "high24h")
    val high24h: Double?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "lastUpdated")
    val lastUpdated: String?,

    @ColumnInfo(name = "low24h")
    val low24h: Double?,

    @ColumnInfo(name = "marketCap")
    val marketCap: Long?,

    @ColumnInfo(name = "marketCapChange24h")
    val marketCapChange24h: Double?,

    @ColumnInfo(name = "marketCapChangePercentage24h")
    val marketCapChangePercentage24h: Double?,

    @ColumnInfo(name = "marketCapRank")
    val marketCapRank: Int?,

    @ColumnInfo(name = "maxSupply")
    val maxSupply: Double?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "priceChange24h")
    val priceChange24h: Double?,

    @ColumnInfo(name = "priceChangePercentage24h")
    val priceChangePercentage24h: Double?,

    @ColumnInfo(name = "symbol")
    val symbol: String?,

    @ColumnInfo(name = "totalSupply")
    val totalSupply: Double?,

    @ColumnInfo(name = "totalVolume")
    val totalVolume: Double?
)