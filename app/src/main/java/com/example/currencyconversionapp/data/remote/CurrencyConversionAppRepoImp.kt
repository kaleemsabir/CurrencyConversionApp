package com.example.currencyconversionapp.data.remote

import com.example.currencyconversionapp.BuildConfig
import com.example.currencyconversionapp.network.ApiInterface
import com.example.currencyconversionapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyConversionAppRepoImp @Inject constructor(private val apiInterface: ApiInterface) :
    CurrencyConversionAppRepo {
    override fun getCurrencyRates() = flow {
        emit(Response.Loading)
        runCatching {
            apiInterface.getCurrencyRates(BuildConfig.Conversion_App_Id)
        }.onSuccess { response ->
            emit(Response.Success(response))
        }.onFailure {
            emit(Response.Error(it))
        }

    }.flowOn(Dispatchers.IO)
}