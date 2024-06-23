package com.example.populationinfoapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.populationinfoapp.data.models.Country

@Composable
fun CountryListScreen(
    modifier: Modifier = Modifier,
    countries: List<Country>
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        countries.forEach { country ->
            CountryListItem(country = country) {
            }
        }
    }
}