package com.example.currencyconversionapp.ui.currencyconversion

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import com.example.currencyconversionapp.R
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.databinding.ActivityCurrencyConversionBinding
import com.example.currencyconversionapp.ui.base.BaseActivity
import com.example.currencyconversionapp.ui.currencyconversion.adapter.CurrencyConversionAdapter
import com.example.currencyconversionapp.ui.currencyconversion.adapter.DropDownAdapter
import com.example.currencyconversionapp.ui.currencyconversion.viewmodel.CurrencyConversionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyConversionActivity :
    BaseActivity<ActivityCurrencyConversionBinding, CurrencyConversionViewModel>() {
    private val currencyConversionAdapter by lazy {
        CurrencyConversionAdapter()
    }

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
        initObserver()
        initErrorObserver()
        initDataObserver()
    }
    private fun initObserver() {
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
    private fun initErrorObserver(){
        viewModel?.errorLive?.observe(this) {
                Toast.makeText(this@CurrencyConversionActivity, getString(it), Toast.LENGTH_LONG)
                    .show()
            }


    }
    private fun initDataObserver() {
        lifecycleScope.launch {
            viewModel?.conversionRates?.collect {
                setCurrencyListData(it)
            }

        }
        lifecycleScope.launch {
            viewModel?.conversionRatesAfterChanging?.collect {
                currencyConversionAdapter.submitList(it)
            }
        }
    }

    private fun setCurrencyListData(list : List<ConversionRatesDbModel>) {
        binding?.apply {
            tvSelectCurrencyRate.apply {
                setAdapter(DropDownAdapter(list, this@CurrencyConversionActivity) { item ->
                    dismissDropDown()
                    setText(item.currencyName)
                    viewModel?.selectedConversionModel = item
                })
            }
            currencyConversionAdapter.submitList(list)
            adapter = currencyConversionAdapter

        }
    }

}