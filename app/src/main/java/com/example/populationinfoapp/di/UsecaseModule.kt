package com.example.populationinfoapp.di

import com.example.populationinfoapp.domain.repoInterface.CountryRepo
import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class UsecaseModule {

    @Provides
    fun provideCountryListUseCase(repo: CountryRepo):CountryListUseCase{
        return CountryListUseCase(repo)
    }
}