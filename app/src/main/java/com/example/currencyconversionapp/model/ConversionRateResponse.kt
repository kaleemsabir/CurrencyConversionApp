package com.example.currencyconversionapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConversionRateResponse(
    val timestamp: Int,
    val base: String,
    val rates: HashMap<String, Double>
):Parcelable{
    companion object {
        val Default= ConversionRateResponse(0,"", HashMap())
    }
}


