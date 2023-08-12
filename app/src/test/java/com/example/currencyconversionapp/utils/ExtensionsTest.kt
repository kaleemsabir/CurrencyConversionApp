package com.example.currencyconversionapp.utils

import com.example.currencyconversionapp.utils.Extensions.toRound2Decimal
import org.junit.Assert.*
import org.junit.Test

class ExtensionsTest {

    @Test
    fun roundTo2DecimalTest(){
        val input = 2.0089
        val expected = 2.00
        assertEquals(input.toRound2Decimal(), expected,0.0)
    }
}