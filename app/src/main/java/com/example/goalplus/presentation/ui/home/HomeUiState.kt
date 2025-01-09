package com.example.goalplus.presentation.ui.home

import com.example.goalplus.domain.model.CompetitionModel

sealed class HomeUiState {
    object Loading:HomeUiState()
    data class Success(val data:List<CompetitionModel>):HomeUiState()
    data class Error(val message:String):HomeUiState()
}