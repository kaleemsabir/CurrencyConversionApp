package com.example.currencyconversionapp.data.local.helper


import com.example.currencyconversionapp.data.local.db.CurrencyConversionDataBase
import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


 class CurrencyConversionAppDbHelperImpl @Inject constructor(private val appDatabase: CurrencyConversionDataBase) :
     CurrencyConversionAppDbHelper {
     override fun getRates(currencyRates: List<Any>) {
         TODO("Not yet implemented")
     }
 }