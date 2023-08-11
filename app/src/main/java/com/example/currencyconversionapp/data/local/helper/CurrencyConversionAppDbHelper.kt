package com.example.currencyconversionapp.data.local.helper

import com.example.currencyconversionapp.data.local.models.ConversionRatesDbModel
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionAppDbHelper {
    suspend  fun saveConversionRatesList(ratesList: List<ConversionRatesDbModel>)
    suspend fun getConversionRatesList(): Flow<List<ConversionRatesDbModel>>
}