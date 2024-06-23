package com.example.populationinfoapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.populationinfoapp.data.models.Country
import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import com.example.populationinfoapp.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val countryListUseCase: CountryListUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _result =
        MutableStateFlow<CountryListUseCase.ResultData>(CountryListUseCase.ResultData.Loading)
    val result: StateFlow<CountryListUseCase.ResultData> get() = _result

    private val _networkState = MutableStateFlow(false)
    val networkState: StateFlow<Boolean> get() = _networkState

    private val _showSnackbar = MutableStateFlow(false)
    val showSnackbar: StateFlow<Boolean> get() = _showSnackbar

    init {
        fetchCountryData()
        checkNetworkState()
    }

    private fun checkNetworkState() {
        _networkState.value = networkHelper.isNetworkAvailable()
        _showSnackbar.value = _networkState.value.not()
    }

    fun fetchCountryData() {
        viewModelScope.launch {
            countryListUseCase.excecute().collectLatest { response ->
                when (response) {
                    is CountryListUseCase.ResultData.Loading -> {
                        _result.value = CountryListUseCase.ResultData.Loading
                    }

                    is CountryListUseCase.ResultData.Success -> {
                        println(response.data)
                        _result.value = CountryListUseCase.ResultData.Success(response.data)
                    }

                    is CountryListUseCase.ResultData.Failure -> {
                        println(response.error)
                        _result.value = CountryListUseCase.ResultData.Failure(response.error)
                    }
                }
            }
        }
    }
}