package com.example.rickandmortyapp.data.provider

import com.example.beersapp.data.common.ApiModule
import com.example.rickandmortyapp.data.network.ApiService
import com.example.rickandmortyapp.data.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
class ApiServiceProvider {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): ApiRepository {
        return ApiRepository(apiService)
    }

}