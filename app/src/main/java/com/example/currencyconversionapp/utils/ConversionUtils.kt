package com.example.currencyconversionapp.utils

object ConversionUtils {

    fun convertCurrencyToSelectedCurrency(currency: Double, selectedCurrency: Double) =
        currency / selectedCurrency

    fun convertCurrencyToRequiredAmount(currency: Double, amount: Double) =
        currency *  amount

}