package com.example.currencyconversionapp.repo

import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelper
import com.example.currencyconversionapp.network.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManagerImpl @Inject constructor(
    private val apiService: ApiInterface,
    private val appDbHelper: CurrencyConversionAppDbHelper
) : AppDataManager {
    override fun getApiHelper() = apiService

    override fun getAppDatabaseHelper(): CurrencyConversionAppDbHelper {
        return appDbHelper
    }

}