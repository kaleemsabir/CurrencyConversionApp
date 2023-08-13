package com.example.currencyconversionapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CurrencyConversionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCurrencyRatesListToDb(data: List<ConversionRatesDbModel>)

    @Query("SELECT *FROM ${Constants.CURRENCY_CONVERSION_TABLE}")
    abstract fun getCurrencyRatesListFromDb(): Flow<List<ConversionRatesDbModel>>
}