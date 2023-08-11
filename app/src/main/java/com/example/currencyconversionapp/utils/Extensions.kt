package com.example.currencyconversionapp.utils

import com.example.currencyconversionapp.data.local.models.ConversionRatesDbModel
import com.example.currencyconversionapp.model.ConversionRateResponse

object Extensions {
    fun ConversionRateResponse.toCurrencyRatesToDbModel() = this.rates.map { (key, value) ->
        ConversionRatesDbModel(
            currencyName = key, currencyRate = value
        )
    }
}