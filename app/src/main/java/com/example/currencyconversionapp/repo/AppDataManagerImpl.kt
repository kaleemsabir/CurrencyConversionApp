package com.example.currencyconversionapp.repo

import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelper
import com.example.currencyconversionapp.data.local.prefs.AppPrefHelper
import com.example.currencyconversionapp.data.remote.CurrencyConversionAppRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManagerImpl @Inject constructor(
    private val apiService: CurrencyConversionAppRepo,
    private val appDbHelper: CurrencyConversionAppDbHelper,
    private val appPrefHelper: AppPrefHelper
) : AppDataManager {
    override fun getApiRepo() = apiService

    override fun getAppDatabaseHelper(): CurrencyConversionAppDbHelper {
        return appDbHelper
    }

    override fun getAppPreferences(): AppPrefHelper {
        return appPrefHelper
    }

}