package com.example.goalplus.domain.repository

import com.example.goalplus.common.NetworkResponseState
import com.example.goalplus.data.dto.Table
import com.example.goalplus.domain.model.CompetitionModel
import com.example.goalplus.domain.model.MatchesModel
import com.example.goalplus.domain.model.MatchesTableModel
import com.example.goalplus.domain.model.StandingModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCompetitions(): Flow<NetworkResponseState<List<CompetitionModel>>>
    suspend fun getMatches(code:String):Flow<NetworkResponseState<List<MatchesModel>>>
    suspend fun getMatchesTable(code:String):Flow<NetworkResponseState<List<StandingModel>>>
}