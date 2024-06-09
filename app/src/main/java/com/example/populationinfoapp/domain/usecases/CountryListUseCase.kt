package com.example.populationinfoapp.domain.usecases

import com.example.populationinfoapp.data.dataSource.repoImpl.CountryRepoImpl
import com.example.populationinfoapp.domain.models.Countries
import com.example.populationinfoapp.domain.repoInterface.CountryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CountryListUseCase(private val repo: CountryRepo = CountryRepoImpl()) {
    suspend fun excecute(): Flow<ResultData> {
        return flow {
            val response = repo.getCountryList()
            if (response.isSuccessful && response.body() != null) {
                emit(ResultData.Success(response.body()!!))
            } else {
                emit(ResultData.Failure(response.message()))
            }
        }.catch {
            emit(ResultData.Failure(it.message ?: "Get country api failed"))
        }
    }

    sealed class ResultData {
        object Loading : ResultData()
        data class Success(val data: Countries) : ResultData()
        data class Failure(val error: String) : ResultData()
    }
}