package com.example.rickandmortyapp.domain

import com.example.rickandmortyapp.data.network.model.CharacterResponse
import com.example.rickandmortyapp.domain.common.BaseResult

interface IApiRepository {
    suspend fun getCharacters(): BaseResult<CharacterResponse>
}