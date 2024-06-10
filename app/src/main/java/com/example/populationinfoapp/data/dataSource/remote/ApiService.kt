package com.example.populationinfoapp.data.dataSource.remote

import com.example.populationinfoapp.data.models.Country
import retrofit2.http.GET

interface ApiService {
    @GET("countries/countries/")
    suspend fun getCountries(): retrofit2.Response<List<Country>>
}