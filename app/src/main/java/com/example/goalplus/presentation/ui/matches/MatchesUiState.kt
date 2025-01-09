package com.example.goalplus.presentation.ui.matches

import com.example.goalplus.domain.model.CompetitionModel
import com.example.goalplus.domain.model.MatchesModel
import com.example.goalplus.presentation.ui.home.HomeUiState

sealed class MatchesUiState {
    object Loading:MatchesUiState()
    data class Success(val data:List<MatchesModel>):MatchesUiState()
    data class Error(val message:String):MatchesUiState()
}