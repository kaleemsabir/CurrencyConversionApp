package com.example.currencyconversionapp.ui.currencyconversion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconversionapp.data.local.models.ConversionRatesDbModel
import com.example.currencyconversionapp.databinding.ItemCurrencyConversionBinding
import com.example.currencyconversionapp.utils.CurrencyAdapterDiff
import javax.inject.Inject


class CurrencyConversionAdapter @Inject constructor():ListAdapter<ConversionRatesDbModel,CurrencyConversionAdapter.CurrencyConversionViewHolder>(CurrencyAdapterDiff) {
    class CurrencyConversionViewHolder(
        private val binding: ItemCurrencyConversionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(conversionData: ConversionRatesDbModel) {
            binding.apply {
                data = conversionData
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyConversionViewHolder {
       return CurrencyConversionViewHolder(
           ItemCurrencyConversionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CurrencyConversionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}