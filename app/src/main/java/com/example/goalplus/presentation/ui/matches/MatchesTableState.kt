package com.example.goalplus.presentation.ui.matches

import com.example.goalplus.domain.model.MatchesModel
import com.example.goalplus.domain.model.MatchesTableModel


sealed class MatchesTableState {
    object Loading:MatchesTableState()
    data class Success(val data:List<MatchesTableModel>):MatchesTableState()
    data class Error(val message:String):MatchesTableState()
}