package com.example.populationinfoapp.domain.usecases

import com.example.populationinfoapp.data.models.Country
import com.example.populationinfoapp.data.repoImpl.CountryRepoImpl
import com.example.populationinfoapp.domain.repoInterface.CountryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryListUseCase @Inject constructor(private val repo: CountryRepo) {
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
        data class Success(val data: List<Country>) : ResultData()
        data class Failure(val error: String) : ResultData()
    }
}