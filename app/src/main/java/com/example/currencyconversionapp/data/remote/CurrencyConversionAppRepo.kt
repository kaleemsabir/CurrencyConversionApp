package com.example.currencyconversionapp.data.remote


import com.example.currencyconversionapp.model.ConversionRateResponse
import com.example.currencyconversionapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionAppRepo {
    fun  getCurrencyRates(): Flow<Response<List<ConversionRateResponse>>>
}