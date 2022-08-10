package com.canerture.cryptocurrencypricetracker.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canerture.cryptocurrencypricetracker.data.model.coinlist.CoinListEntity
import com.canerture.cryptocurrencypricetracker.data.model.coinmarket.CoinMarketsEntity

@Database(
    entities = [CoinListEntity::class, CoinMarketsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsRoomDB : RoomDatabase() {
    abstract fun coinsDAO(): CoinsDAO
}