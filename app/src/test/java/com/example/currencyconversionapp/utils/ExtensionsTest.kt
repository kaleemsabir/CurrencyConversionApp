package com.example.currencyconversionapp.utils

import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.model.ConversionRateResponse
import com.example.currencyconversionapp.utils.Extensions.toCurrencyRatesToDbModel
import com.example.currencyconversionapp.utils.Extensions.toRoundDecimal
import org.junit.Assert.*
import org.junit.Test

class ExtensionsTest {

    @Test
    fun currencyRateConvertCorrectly() {
        val response = ConversionRateResponse(
            timestamp = 0,
            base = "USD",
            rates = hashMapOf(
                "EUR" to 0.85,
                "GBP" to 0.73,
                "JPY" to 110.50
            )
        )

        var expectedList = listOf(
            ConversionRatesDbModel(currencyName = "EUR", currencyRate = 0.85),
            ConversionRatesDbModel(currencyName = "GBP", currencyRate =  0.73),
            ConversionRatesDbModel(currencyName = "JPY", currencyRate = 110.50)
        )

        var actualList = response.toCurrencyRatesToDbModel()
        actualList =  actualList.sortedByDescending { it.currencyName }
        expectedList = expectedList.sortedByDescending { it.currencyName }
        assertEquals(expectedList, actualList)
    }

    @Test
    fun currencyRateWithEmptyList() {
        val response = ConversionRateResponse(
            timestamp = 0,
            base = "",
            rates = hashMapOf()
        )

        val expectedList = emptyList<ConversionRatesDbModel>()

        val actualList = response.toCurrencyRatesToDbModel()

        assertEquals(expectedList, actualList)
    }

    @Test
    fun roundToDecimalTest(){
        val input = 2.00895
        val expected = 2.0089
        assertEquals(input.toRoundDecimal(), expected,0.0)
    }
}