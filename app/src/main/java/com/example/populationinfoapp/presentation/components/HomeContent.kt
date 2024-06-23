package com.example.populationinfoapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import com.example.populationinfoapp.presentation.viewModel.MainActivityViewModel

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel
) {
    val result = viewModel.result.collectAsState()
    val networkState: Boolean by viewModel.networkState.collectAsState()

    Surface(modifier.fillMaxSize()) {
        println("network state $networkState")
        when (result.value) {
            is CountryListUseCase.ResultData.Loading -> LoadingScreen()
            is CountryListUseCase.ResultData.Failure -> ShowEmptyScreen()
            is CountryListUseCase.ResultData.Success -> CountryListScreen(countries = (result.value as CountryListUseCase.ResultData.Success).data)
        }
    }
}
