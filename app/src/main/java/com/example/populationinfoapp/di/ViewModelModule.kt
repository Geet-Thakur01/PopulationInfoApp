package com.example.populationinfoapp.di

import com.example.populationinfoapp.domain.usecases.CountryListUseCase
import com.example.populationinfoapp.presentation.viewModel.MainActivityViewModel
import com.example.populationinfoapp.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class ViewModelModule {
    @Provides
    fun provideMainActivityViewModel(
        countryListUseCase: CountryListUseCase,
        networkHelper: NetworkHelper
    ): MainActivityViewModel {
        return MainActivityViewModel(countryListUseCase,networkHelper)
    }
}