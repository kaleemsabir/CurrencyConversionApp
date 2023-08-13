package com.example.currencyconversionapp.data.local.prefs

interface AppPrefHelper {
    fun saveTimeStamp(timeStamp: Long)
    fun getTimeStamp(): Long
}