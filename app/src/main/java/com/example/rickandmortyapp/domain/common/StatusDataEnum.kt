package com.example.rickandmortyapp.domain.common

enum class Status { LOADING, SUCCESS, ERROR }

data class StatusData<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Exception? = null
)

