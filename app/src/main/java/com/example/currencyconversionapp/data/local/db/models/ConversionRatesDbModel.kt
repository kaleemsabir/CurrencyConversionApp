package com.example.currencyconversionapp.data.local.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconversionapp.utils.Constants

@Entity(tableName = Constants.CURRENCY_CONVERSION_TABLE)
data class ConversionRatesDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var currencyName: String = "",
    var currencyRate: Double = 0.0
)