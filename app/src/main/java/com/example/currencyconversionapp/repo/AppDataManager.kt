package com.example.currencyconversionapp.repo

import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelper
import com.example.currencyconversionapp.network.ApiInterface


interface AppDataManager {
    fun getApiHelper(): ApiInterface
    fun getAppDatabaseHelper(): CurrencyConversionAppDbHelper
}