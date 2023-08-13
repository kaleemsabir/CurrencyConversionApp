package com.example.currencyconversionapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconversionapp.data.local.dao.CurrencyConversionDao
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.utils.Constants

@Database(entities = [ConversionRatesDbModel::class], version = 1, exportSchema = false)
abstract class CurrencyConversionDataBase () : RoomDatabase() {
    abstract fun currencyConversionDao(): CurrencyConversionDao

    companion object {
        @Volatile
        private var instance: CurrencyConversionDataBase? = null

        fun getInstance(context: Context): CurrencyConversionDataBase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CurrencyConversionDataBase::class.java,
            Constants.CURRENCY_CONVERSION_APP_DB_NAME
        ).build()

    }

}