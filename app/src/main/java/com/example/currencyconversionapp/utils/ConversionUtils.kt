package com.example.currencyconversionapp.utils

object ConversionUtils {

    fun convertCurrencyToSelectedCurrency(currency: Double, selectedCurrency: Double) =
        currency / selectedCurrency

    fun convertCurrencyToRequiredAmount(currency: Double, amount: Double) =
        currency *  amount

    fun isTimePassesForFetchData(timeStamp: Long): Boolean {

        val currentTimeMillis = System.currentTimeMillis()

        val timeDifferenceMillis = currentTimeMillis - timeStamp

        val twentyFourHoursInMillis = 86400000L

        return timeDifferenceMillis >= twentyFourHoursInMillis

    }

}