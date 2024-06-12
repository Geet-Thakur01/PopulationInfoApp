package com.example.populationinfoapp.di

import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import com.example.populationinfoapp.presentation.viewModel.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class ViewModelModule {
    @Provides
    fun provideMainActivityViewModel(countryListUseCase: CountryListUseCase): MainActivityViewModel {
        return MainActivityViewModel(countryListUseCase)
    }
}