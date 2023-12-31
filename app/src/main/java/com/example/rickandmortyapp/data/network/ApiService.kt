package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}