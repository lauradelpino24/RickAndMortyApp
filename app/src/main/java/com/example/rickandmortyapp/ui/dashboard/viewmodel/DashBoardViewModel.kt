package com.example.rickandmortyapp.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.network.model.Results
import com.example.rickandmortyapp.domain.common.BaseResult
import com.example.rickandmortyapp.domain.common.Status
import com.example.rickandmortyapp.domain.common.StatusData
import com.example.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    private val _mutableStateList =
        MutableStateFlow<StatusData<List<Results>>>(StatusData(responseType = Status.LOADING))
    val stateList: StateFlow<StatusData<List<Results>>> = _mutableStateList.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            _mutableStateList.value = StatusData(responseType = Status.LOADING)
            val result = withContext(Dispatchers.IO) {
                getCharactersUseCase.invoke()
            }
            when (result) {
                is BaseResult.Error -> {
                    _mutableStateList.value =
                        StatusData(responseType = Status.ERROR, error = result.exception)
                }

                is BaseResult.Success -> {
                    val filteredResults = result.data?.results?.filter { character ->
                        character.name.contains(query, ignoreCase = true)
                    }
                    _mutableStateList.value =
                        StatusData(responseType = Status.SUCCESS, data = filteredResults)
                }
            }
        }
    }

}
