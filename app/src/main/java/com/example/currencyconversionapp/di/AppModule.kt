package com.example.currencyconversionapp.di

import android.app.Application
import android.content.Context
import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelper
import com.example.currencyconversionapp.data.local.helper.CurrencyConversionAppDbHelperImpl
import com.example.currencyconversionapp.repo.AppDataManager
import com.example.currencyconversionapp.repo.AppDataManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDataManager(dataManagerImpl: AppDataManagerImpl): AppDataManager {
        return dataManagerImpl
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideAppDbHelper(dbHelperImpl: CurrencyConversionAppDbHelperImpl): CurrencyConversionAppDbHelper {
        return dbHelperImpl
    }

}