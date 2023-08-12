package com.example.currencyconversionapp.utils

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.TimeUnit


class ConversionUtilsTest {

    @Test
    fun convertUsdToEuro(){
        val usd = 285.6
        val euro =  380.0
        val expectedResult = 0.75
        val totalEuroInUsd = ConversionUtils.convertCurrencyToSelectedCurrency(usd,euro)
        assertEquals(totalEuroInUsd,expectedResult,0.01)
    }
    @Test
    fun convertEuroToUsd(){
        val usd = 285.6
        val euro =  380.0
        val expectedResult = 1.33
        val totalUsdInEuro = ConversionUtils.convertCurrencyToSelectedCurrency(euro,usd)
        assertEquals(totalUsdInEuro,expectedResult,0.01)
    }
    @Test
    fun testConvertCurrencyToRequiredAmount() {
        val currency = 1.5
        val amount = 10.0
        val expectedResult = 15.0

        val convertedAmount = ConversionUtils.convertCurrencyToRequiredAmount(currency, amount)

        assertEquals(expectedResult, convertedAmount,0.1)
    }

    @Test
    fun isTimeThirtyMinutesPassedTest(){
        val isPassed = ConversionUtils.isTimePassesForFetchData(System.currentTimeMillis()- TimeUnit.HOURS.toMillis(1),System.currentTimeMillis())
        assertTrue(isPassed)
    }
    @Test
    fun isTimeThirtyMinutesNotPassedTest(){
     val isPassed = ConversionUtils.isTimePassesForFetchData(System.currentTimeMillis(),System.currentTimeMillis())
        assertFalse(isPassed)
    }
}