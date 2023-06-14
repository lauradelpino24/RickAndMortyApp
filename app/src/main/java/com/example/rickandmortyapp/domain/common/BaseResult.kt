package com.example.rickandmortyapp.domain.common

sealed class BaseResult<out T : Any> {
    data class Success<T : Any>(val data: T?) : BaseResult<T>()
    data class Error(val exception: Exception) : BaseResult<Nothing>()
}