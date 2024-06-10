package com.example.populationinfoapp.domain.repoInterface

import com.example.populationinfoapp.data.models.Country
import retrofit2.Response

interface CountryRepo {
    suspend fun getCountryList(): Response<List<Country>>
}