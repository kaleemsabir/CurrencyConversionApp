package com.example.currencyconversionapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconversionapp.data.local.db.dao.CurrencyConversionDao
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.utils.Constants

@Database(entities = [ConversionRatesDbModel::class], version = 1, exportSchema = false)
// Because room  db is abstract class that s why we make it abstract
// Because we make it abstract that s we make module injection of it
abstract class CurrencyConversionDataBase () : RoomDatabase() {
    // abstract function to return DAO
    abstract fun currencyConversionDao(): CurrencyConversionDao

    companion object {
        // @Volatile  ensures that changes made to the variable are immediately visible to other threads
        @Volatile
        private var instance: CurrencyConversionDataBase? = null

        fun getInstance(context: Context): CurrencyConversionDataBase {
            //Only one thread can execute the code within the synchronized block at any given time.
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