package com.example.currencyconversionapp.data.local.db.helper


import com.example.currencyconversionapp.data.local.db.CurrencyConversionDataBase
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
// its treat as a Singleton and make object lifetime of it
// @inject dependency should be provided by hilt
class CurrencyConversionAppDbHelperImpl @Inject constructor(private val appDatabase: CurrencyConversionDataBase) :
    CurrencyConversionAppDbHelper {
    override suspend fun saveConversionRatesList(ratesList: List<ConversionRatesDbModel>) {
        appDatabase.currencyConversionDao().saveCurrencyRatesListToDb(ratesList)
    }

    override suspend fun getConversionRatesList(): Flow<List<ConversionRatesDbModel>> {
       return appDatabase.currencyConversionDao().getCurrencyRatesListFromDb()
    }

}