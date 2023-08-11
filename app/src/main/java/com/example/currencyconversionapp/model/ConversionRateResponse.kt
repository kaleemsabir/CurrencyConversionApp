package com.example.currencyconversionapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConversionRateResponse(
    val timestamp: Int,
    val base: String,
    var rates: HashMap<String, Double>
):Parcelable{
    companion object {
        val Default= ConversionRateResponse(0,"", HashMap())
    }
}


