package com.example.populationinfoapp.di

import android.app.Application
import android.content.Context
import com.example.populationinfoapp.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkProviderModule {

    @Provides
    @Singleton
    fun provideNetwork(@ApplicationContext context: Context):NetworkHelper{
        return NetworkHelper(context)
    }
}