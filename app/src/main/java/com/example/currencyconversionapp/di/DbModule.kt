package com.example.currencyconversionapp.di

import android.content.Context
import com.example.currencyconversionapp.data.local.db.CurrencyConversionDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun provideCurrencyAppDatabase(@ApplicationContext context: Context) =
        CurrencyConversionDataBase.getInstance(context)
}