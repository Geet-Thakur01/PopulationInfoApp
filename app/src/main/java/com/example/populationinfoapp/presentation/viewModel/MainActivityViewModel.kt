package com.example.populationinfoapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.populationinfoapp.data.models.Country
import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivityViewModel(private val countryListUseCase: CountryListUseCase = CountryListUseCase()) :
    ViewModel() {

    private val _countries = mutableStateOf<List<Country>>(emptyList<Country>())
    val countries: State<List<Country>> get() = _countries

    init {
        fetchCountryData()
    }

    fun fetchCountryData() {
        viewModelScope.launch {
            countryListUseCase.excecute().collectLatest { result ->
                when (result) {
                    is CountryListUseCase.ResultData.Loading -> {}
                    is CountryListUseCase.ResultData.Success -> {
                        println(result.data)
                        _countries.value=result.data
                    }

                    is CountryListUseCase.ResultData.Failure -> {
                        println(result.error)
                    }
                }
            }
        }
    }
}