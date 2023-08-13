package com.example.currencyconversionapp.utils

import com.example.currencyconversionapp.utils.Extensions.toRoundDecimal
import java.util.concurrent.TimeUnit

object ConversionUtils {

    fun convertCurrencyToSelectedCurrency(currency: Double, selectedCurrency: Double) =
        (currency / selectedCurrency).toRoundDecimal()

    fun convertCurrencyToRequiredAmount(currency: Double, amount: Double) =
        (currency *  amount).toRoundDecimal()

    fun isTimePassesForFetchData(timeStamp: Long,currentTimeStamp : Long): Boolean {

        val timeDifferenceMillis = currentTimeStamp - timeStamp

        val thirtyMinutesInMillis: Long = TimeUnit.MINUTES.toMillis(30)

        return timeDifferenceMillis >= thirtyMinutesInMillis

    }

}