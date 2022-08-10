package com.canerture.cryptocurrencypricetracker.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListEntity
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsEntity

@Dao
interface CoinsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinList(items: List<CoinListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinMarkets(items: List<CoinMarketsEntity>)

    @Query("SELECT * FROM coin_list ORDER BY id ASC")
    suspend fun getCoinList(): List<CoinListEntity>

    @Query("SELECT * FROM coin_markets ORDER BY id ASC")
    suspend fun getCoinMarkets(): List<CoinMarketsEntity>

    @Query("SELECT * FROM coin_list WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    suspend fun searchCoin(searchQuery: String): List<CoinListEntity>

    @Query("DELETE FROM coin_list")
    suspend fun deleteCoinList()

    @Query("DELETE FROM coin_markets")
    suspend fun deleteCoinMarkets()
}