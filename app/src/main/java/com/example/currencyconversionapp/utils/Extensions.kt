package com.example.currencyconversionapp.utils

import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.model.ConversionRateResponse
import java.math.RoundingMode
import java.text.DecimalFormat

object Extensions {
    fun ConversionRateResponse.toCurrencyRatesToDbModel() = this.rates.map { (key, value) ->
        ConversionRatesDbModel(
            currencyName = key, currencyRate = value.toRound2Decimal()
        )
    }

    fun Double.toRound2Decimal() : Double{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundOff = df.format(this)
        return roundOff.toDouble()
    }
}