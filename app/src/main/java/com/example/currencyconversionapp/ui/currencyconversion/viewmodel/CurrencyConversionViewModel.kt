package com.example.currencyconversionapp.ui.currencyconversion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconversionapp.data.local.models.ConversionRatesDbModel
import com.example.currencyconversionapp.repo.AppDataManager
import com.example.currencyconversionapp.utils.Extensions.toCurrencyRatesToDbModel
import com.example.currencyconversionapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(private val repository: AppDataManager): ViewModel() {
    // Created State Flow for the Progress Bar.
    private val _progressBar = MutableStateFlow(false)
    val progressBar = _progressBar.asStateFlow()

    // Created State Flow for the Currency rate  List.
    private val _conversionRates = MutableStateFlow(emptyList<ConversionRatesDbModel>())
    val conversionRates = _conversionRates.asStateFlow()

    init {
        fetchCurrencyConversion()
    }

    private fun fetchCurrencyConversion(){
        viewModelScope.launch {
            repository.getAppDatabaseHelper().getConversionRatesList().collect{
                if(it.isNotEmpty()){
                    _conversionRates.value = it
                }else{
                    fetchCurrencyFromNetwork()
                }
            }
        }

    }

    private fun fetchCurrencyFromNetwork() {
        viewModelScope.launch {
            repository.getApiRepo().getCurrencyRates().collect{ response->
                when(response){
                    is Response.Loading ->{
                        _progressBar.value = true
                    }
                    is Response.Success ->{
                        _progressBar.value = false
                        _conversionRates.value = response.item.toCurrencyRatesToDbModel()
                        saveCurrencyListInDb(_conversionRates.value)
                    }
                    is Response.Error ->{
                        _progressBar.value = false
                    }
                }
            }
        }
    }

    private fun saveCurrencyListInDb(ratesList: List<ConversionRatesDbModel>){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAppDatabaseHelper().saveConversionRatesList(ratesList)
        }
    }


}