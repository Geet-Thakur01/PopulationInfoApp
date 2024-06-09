package com.example.populationinfoapp.data.dataSource.repoImpl

import com.example.populationinfoapp.data.dataSource.remote.ApiClient
import com.example.populationinfoapp.data.dataSource.remote.ApiService
import com.example.populationinfoapp.domain.models.Countries
import com.example.populationinfoapp.domain.repoInterface.CountryRepo
import retrofit2.Response

class CountryRepoImpl(private val apiService: ApiService = ApiClient.api) : CountryRepo {
    override suspend fun getCountryList(): Response<Countries> {
        return apiService.getCountries()
    }
}