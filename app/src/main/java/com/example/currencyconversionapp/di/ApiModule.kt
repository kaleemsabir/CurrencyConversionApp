package com.example.currencyconversionapp.di

import com.example.currencyconversionapp.BuildConfig
import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelper
import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelperImpl
import com.example.currencyconversionapp.data.remote.CurrencyConversionAppRepo
import com.example.currencyconversionapp.data.remote.CurrencyConversionAppRepoImp
import com.example.currencyconversionapp.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    // Provides Retrofit Client
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(getHTTPClient())
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

    private fun getHTTPClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()


            chain.proceed(builder.build())
        }

        return httpClient.build()
    }
}