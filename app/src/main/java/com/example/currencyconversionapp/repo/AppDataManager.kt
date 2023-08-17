package com.example.currencyconversionapp.repo

import com.example.currencyconversionapp.data.local.db.helper.CurrencyConversionAppDbHelper
import com.example.currencyconversionapp.data.local.prefs.AppPrefHelper
import com.example.currencyconversionapp.data.remote.CurrencyConversionAppRepo


interface AppDataManager {
    fun getApiRepo(): CurrencyConversionAppRepo
    fun getAppDatabaseHelper(): CurrencyConversionAppDbHelper
    fun getAppPreferences(): AppPrefHelper
}