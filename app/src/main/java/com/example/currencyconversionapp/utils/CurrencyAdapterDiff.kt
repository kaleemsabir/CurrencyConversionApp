package com.example.currencyconversionapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel

object CurrencyAdapterDiff : DiffUtil.ItemCallback<ConversionRatesDbModel>() {

    override fun areItemsTheSame(oldItem: ConversionRatesDbModel, newItem: ConversionRatesDbModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ConversionRatesDbModel, newItem: ConversionRatesDbModel): Boolean {
        return oldItem == newItem
    }

}