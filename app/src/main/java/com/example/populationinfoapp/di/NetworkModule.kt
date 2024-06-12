package com.example.populationinfoapp.di

import com.example.populationinfoapp.data.dataSource.remote.ApiClient
import com.example.populationinfoapp.data.dataSource.remote.ApiService
import com.example.populationinfoapp.data.repoImpl.CountryRepoImpl
import com.example.populationinfoapp.domain.repoInterface.CountryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
         return ApiClient.api
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService):CountryRepo{
        return CountryRepoImpl(apiService)
    }

}