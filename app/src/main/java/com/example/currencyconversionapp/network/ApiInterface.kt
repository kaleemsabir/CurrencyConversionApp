package com.example.currencyconversionapp.network


import com.example.currencyconversionapp.model.ConversionRateResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("latest.json?app_id=7e50057653cf4f9fae152b5e0bdfb932")
    suspend fun getCurrencyRates(): ConversionRateResponse
}