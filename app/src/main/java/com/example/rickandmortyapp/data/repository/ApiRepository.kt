package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.network.model.CharacterResponse
import com.example.rickandmortyapp.data.network.ApiService
import com.example.rickandmortyapp.domain.IApiRepository
import com.example.rickandmortyapp.domain.common.BaseResult
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) : IApiRepository {

    override suspend fun getCharacters(): BaseResult<CharacterResponse> {
        val response = apiService.getCharacters()
        return if (response.isSuccessful) {
            BaseResult.Success(response.body())
        } else {
            return BaseResult.Error(Exception(response.message()))
        }
    }

}