package com.example.currencyconversionapp.ui.currencyconversion

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.currencyconversionapp.R
import com.example.currencyconversionapp.data.local.models.ConversionRatesDbModel
import com.example.currencyconversionapp.databinding.ActivityCurrencyConversionBinding
import com.example.currencyconversionapp.ui.base.BaseActivity
import com.example.currencyconversionapp.ui.currencyconversion.adapter.DropDownAdapter
import com.example.currencyconversionapp.ui.currencyconversion.viewmodel.CurrencyConversionViewModel
import com.example.currencyconversionapp.utils.Extensions.toCurrencyRatesToDbModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyConversionActivity :
    BaseActivity<ActivityCurrencyConversionBinding, CurrencyConversionViewModel>() {

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_currency_conversion
    }

    override fun getActViewModel(): Class<CurrencyConversionViewModel> {
        return CurrencyConversionViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        initProgressObserver()
        initDataObserver()
    }
    private fun initProgressObserver() {
        lifecycleScope.launch {
            viewModel?.progressBar?.collect {
                when {
                    it -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    else -> {
                        binding?.progressBar?.visibility = View.GONE
                    }
                }
            }
        }
    }
    private fun initDataObserver() {
        lifecycleScope.launch {
            viewModel?.conversionRates?.collect {
                setCurrencyListInDropDown(it)
            }
        }
    }

    private fun setCurrencyListInDropDown(list : List<ConversionRatesDbModel>) {
        binding?.tvSelectCurrencyRate?.apply {
            setAdapter(DropDownAdapter(list, this@CurrencyConversionActivity) { item ->
                dismissDropDown()
                setText(item.currencyName)
            })
        }
    }

}