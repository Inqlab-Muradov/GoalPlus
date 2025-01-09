package com.example.goalplus.data.source.remote

import com.example.goalplus.common.NetworkResponseState
import com.example.goalplus.data.dto.CompetitionsDto
import com.example.goalplus.data.dto.MatchesDto
import com.example.goalplus.data.dto.MatchesTableDto

interface RemoteDataSource {
    suspend fun getCompetitions(): NetworkResponseState<CompetitionsDto>
    suspend fun getMatches(code:String) : NetworkResponseState<MatchesDto>
    suspend fun getMatchesTable(code:String):NetworkResponseState<MatchesTableDto>
}