package com.example.populationinfoapp.domain.repoInterface

import com.example.populationinfoapp.domain.models.Countries
import retrofit2.Response

interface CountryRepo {
    suspend fun getCountryList():Response<Countries>
}