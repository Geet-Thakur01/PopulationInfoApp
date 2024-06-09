package com.example.populationinfoapp.data.dataSource.remote

import com.example.populationinfoapp.domain.models.Countries
import retrofit2.http.GET

interface ApiService {
    @GET("countries/countries/")
    suspend fun getCountries(): retrofit2.Response<Countries>
}