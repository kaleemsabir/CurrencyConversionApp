package com.example.currencyconversionapp.ui.currencyconversion.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconversionapp.R
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel
import com.example.currencyconversionapp.repo.AppDataManager
import com.example.currencyconversionapp.utils.ConversionUtils
import com.example.currencyconversionapp.utils.Extensions.toCurrencyRatesToDbModel
import com.example.currencyconversionapp.utils.Extensions.toRound2Decimal
import com.example.currencyconversionapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(private val repository: AppDataManager) :
    ViewModel() {
    // Created State Flow for the Progress Bar.
    private val _progressBar = MutableStateFlow(false)
    val progressBar = _progressBar.asStateFlow()

    // Created State Flow for the Currency rate  List.
    private val _conversionRates = MutableStateFlow(emptyList<ConversionRatesDbModel>())

    val conversionRates = _conversionRates.asStateFlow()
    private val _conversionRatesAfterChanging =
        MutableStateFlow(emptyList<ConversionRatesDbModel>())
    val conversionRatesAfterChanging = _conversionRatesAfterChanging.asStateFlow()

    val errorLive = MutableLiveData<Int>()

    var amount = MutableLiveData<String>()
    var selectedConversionModel: ConversionRatesDbModel? = null

    init {
        fetchCurrencyConversion()
    }

    private fun fetchCurrencyConversion() {
        viewModelScope.launch {
            if (ConversionUtils.isTimePassesForFetchData(
                    repository.getAppPreferences().getTimeStamp())) {
                fetchCurrencyFromNetwork()
            } else {
                repository.getAppDatabaseHelper().getConversionRatesList().collect {
                    if (it.isNotEmpty()) {
                        _conversionRates.value = it
                    } else {
                        fetchCurrencyFromNetwork()
                    }
                }
            }
        }
    }

    private fun fetchCurrencyFromNetwork() {
        viewModelScope.launch {
            repository.getApiRepo().getCurrencyRates().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _progressBar.value = true
                    }
                    is Response.Success -> {
                        _progressBar.value = false
                        _conversionRates.value = response.item.toCurrencyRatesToDbModel()
                        repository.getAppPreferences().saveTimeStamp(System.currentTimeMillis())
                        saveCurrencyListInDb(_conversionRates.value)
                    }
                    is Response.Error -> {
                        _progressBar.value = false
                    }
                }
            }
        }
    }

    private fun saveCurrencyListInDb(ratesList: List<ConversionRatesDbModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAppDatabaseHelper().saveConversionRatesList(ratesList)
        }
    }

    fun convertCurrency() {
        if (amount.value.isNullOrEmpty()) {
            errorLive.value = R.string.enter_the_conversion_amount
            return
        } else if (selectedConversionModel == null) {
            errorLive.value = R.string.select_the_conversion_model
            return
        }

        viewModelScope.launch(Dispatchers.Default) {
            val tempList: MutableList<ConversionRatesDbModel> = ArrayList()
            _conversionRates.let { conversionRates ->
                conversionRates.value.forEach {
                    val newCurrencyRate = ConversionUtils.convertCurrencyToSelectedCurrency(
                        it.currencyRate,
                        selectedConversionModel!!.currencyRate
                    )
                    val totalCurrencyRate = ConversionUtils.convertCurrencyToRequiredAmount(
                        newCurrencyRate,
                        amount.value!!.toDouble()
                    )
                    tempList.add(
                        ConversionRatesDbModel(
                            currencyName = it.currencyName,
                            currencyRate = totalCurrencyRate.toRound2Decimal()
                        )
                    )
                }
            }
            _conversionRatesAfterChanging.value = tempList
        }
    }


}