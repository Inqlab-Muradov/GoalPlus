package com.example.goalplus.data.source.remote

import com.example.goalplus.common.API_KEY
import com.example.goalplus.common.NetworkResponseState
import com.example.goalplus.data.api.ApiService
import com.example.goalplus.data.dto.CompetitionsDto
import com.example.goalplus.data.dto.MatchesDto
import com.example.goalplus.data.dto.MatchesTableDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api:ApiService
):RemoteDataSource {
    override suspend fun getCompetitions(): NetworkResponseState<CompetitionsDto> {
        return try {
            val response = api.getCompetitions().body()
            NetworkResponseState.Success(response)
        }catch (e:Exception){
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getMatches(code:String): NetworkResponseState<MatchesDto> {
        return try {
            val response = api.getMatches(code).body()
            NetworkResponseState.Success(response)
        }catch (e:Exception){
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun getMatchesTable(code: String): NetworkResponseState<MatchesTableDto> {
        return try {
            val response = api.getMatchesTable(code).body()
            NetworkResponseState.Success(response)
        }catch (e:Exception){
            return NetworkResponseState.Error(e)
        }
    }

}