package com.example.currencyconversionapp.network


import com.example.currencyconversionapp.model.ConversionRateResponse
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/")
    suspend fun getCurrencyRates(): List<ConversionRateResponse>

}