package com.example.currencyconversionapp.utils

import java.util.concurrent.TimeUnit

object ConversionUtils {

    fun convertCurrencyToSelectedCurrency(currency: Double, selectedCurrency: Double) =
        currency / selectedCurrency

    fun convertCurrencyToRequiredAmount(currency: Double, amount: Double) =
        currency *  amount

    fun isTimePassesForFetchData(timeStamp: Long): Boolean {

        val currentTimeMillis = System.currentTimeMillis()

        val timeDifferenceMillis = currentTimeMillis - timeStamp

        val thirtyMinutesInMillis: Long = TimeUnit.MINUTES.toMillis(30)

        return timeDifferenceMillis >= thirtyMinutesInMillis

    }

}