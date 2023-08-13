package com.example.currencyconversionapp.di

import com.example.currencyconversionapp.BuildConfig
import com.example.currencyconversionapp.data.remote.CurrencyConversionAppRepo
import com.example.currencyconversionapp.data.remote.CurrencyConversionAppRepoImp
import com.example.currencyconversionapp.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    // Provides Retrofit Client
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    // Provides Api Interface.

    @Singleton
    @Provides
    fun providesApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyConversionRepo(currencyConversionRepoImpl: CurrencyConversionAppRepoImp): CurrencyConversionAppRepo {
        return currencyConversionRepoImpl
    }



}