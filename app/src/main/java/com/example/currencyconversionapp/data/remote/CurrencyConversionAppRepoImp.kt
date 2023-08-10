package com.example.currencyconversionapp.data.remote

import com.example.currencyconversionapp.network.ApiInterface
import com.example.currencyconversionapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CurrencyConversionAppRepoImp(private val apiInterface: ApiInterface) :
    CurrencyConversionAppRepo {
    override fun getCurrencyRates() = flow {

        emit(Response.Loading)

        runCatching {

            apiInterface.getCurrencyRates()

        }.onSuccess { response ->

            emit(Response.Success(response))

        }.onFailure {

            emit(Response.Error(it))

        }

    }.flowOn(Dispatchers.IO)
}