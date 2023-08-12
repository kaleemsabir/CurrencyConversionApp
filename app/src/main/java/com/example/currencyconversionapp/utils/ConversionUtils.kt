package com.example.currencyconversionapp.utils

import com.example.currencyconversionapp.utils.Extensions.toRound2Decimal
import java.util.concurrent.TimeUnit

object ConversionUtils {

    fun convertCurrencyToSelectedCurrency(currency: Double, selectedCurrency: Double) =
        (currency / selectedCurrency).toRound2Decimal()

    fun convertCurrencyToRequiredAmount(currency: Double, amount: Double) =
        (currency *  amount).toRound2Decimal()

    fun isTimePassesForFetchData(timeStamp: Long,currentTimeStamp : Long): Boolean {

        val timeDifferenceMillis = currentTimeStamp - timeStamp

        val thirtyMinutesInMillis: Long = TimeUnit.MINUTES.toMillis(30)

        return timeDifferenceMillis >= thirtyMinutesInMillis

    }

}