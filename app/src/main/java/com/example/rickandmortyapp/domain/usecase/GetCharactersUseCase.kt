package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.data.network.model.CharacterResponse
import com.example.rickandmortyapp.data.repository.ApiRepository
import com.example.rickandmortyapp.domain.common.BaseResult
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: ApiRepository) {

    suspend operator fun invoke(): BaseResult<CharacterResponse> {
        return repository.getCharacters()
    }

}