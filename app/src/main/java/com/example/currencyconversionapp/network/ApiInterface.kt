package com.example.currencyconversionapp.network


import com.example.currencyconversionapp.model.ConversionRateResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("latest.json")
    suspend fun getCurrencyRates(@Query ("app_id") appID :String): ConversionRateResponse
}