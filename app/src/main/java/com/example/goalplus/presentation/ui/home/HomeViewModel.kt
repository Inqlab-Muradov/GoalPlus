package com.example.goalplus.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalplus.common.NetworkResponseState
import com.example.goalplus.domain.usecase.CompetitionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val competitionsUseCase: CompetitionsUseCase
) : ViewModel() {

    private val _competitionsState = MutableLiveData<HomeUiState>()
    val competitions: LiveData<HomeUiState> get() = _competitionsState

    init {
        getCompetitions()
    }

    private fun getCompetitions() {
        viewModelScope.launch {
            competitionsUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        it.result?.let { list ->
                            _competitionsState.postValue(HomeUiState.Success(list))
                        }
                    }

                    is NetworkResponseState.Error -> {
                        _competitionsState.postValue(HomeUiState.Error(it.exception.toString()))
                    }

                    is NetworkResponseState.Loading -> {
                        _competitionsState.postValue(HomeUiState.Loading)
                    }
                }
            }
        }
    }
}