package com.example.populationinfoapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivityViewModel(private val countryListUseCase: CountryListUseCase = CountryListUseCase()) :
    ViewModel() {

    private val _countries =
        MutableStateFlow<CountryListUseCase.ResultData>(CountryListUseCase.ResultData.Loading)
    val countries: StateFlow<CountryListUseCase.ResultData> get() = _countries

    fun fetchCountryData() {
        viewModelScope.launch {
            countryListUseCase.excecute().collectLatest { result ->
                when (result) {
                    is CountryListUseCase.ResultData.Loading -> {}
                    is CountryListUseCase.ResultData.Success -> {
                        println(result.data)
                    }

                    is CountryListUseCase.ResultData.Failure -> {
                        println(result.error)
                    }
                }
            }
        }
    }
}