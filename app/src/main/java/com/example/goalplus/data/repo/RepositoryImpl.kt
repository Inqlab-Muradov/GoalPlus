package com.example.goalplus.data.repo

import com.example.goalplus.common.NetworkResponseState
import com.example.goalplus.data.dto.Table
import com.example.goalplus.data.mapper.toCompetitionModel
import com.example.goalplus.data.mapper.toMatchesModel
import com.example.goalplus.data.mapper.toMatchesTableModel
import com.example.goalplus.data.mapper.toStandingModel
import com.example.goalplus.data.source.remote.RemoteDataSource
import com.example.goalplus.domain.model.CompetitionModel
import com.example.goalplus.domain.model.MatchesModel
import com.example.goalplus.domain.model.MatchesTableModel
import com.example.goalplus.domain.model.StandingModel
import com.example.goalplus.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl@Inject constructor(
    private val remoteDataSource: RemoteDataSource
):Repository {
    override suspend fun getCompetitions(): Flow<NetworkResponseState<List<CompetitionModel>>> = flow {
        emit(NetworkResponseState.Loading)
        when(val response = remoteDataSource.getCompetitions()){
            is NetworkResponseState.Success->{
                emit(NetworkResponseState.Success(response.result?.competitions?.toCompetitionModel()))
            }
            is NetworkResponseState.Error->{
                emit(NetworkResponseState.Error(response.exception))
            }
            else->{}
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMatches(code:String): Flow<NetworkResponseState<List<MatchesModel>>> = flow {
        emit(NetworkResponseState.Loading)
        when(val response = remoteDataSource.getMatches(code)){
            is NetworkResponseState.Success->{
                emit(NetworkResponseState.Success(response.result?.matches?.toMatchesModel()))
            }
            is NetworkResponseState.Error->{
                emit(NetworkResponseState.Error(response.exception))
            }
            else->{}
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMatchesTable(code: String): Flow<NetworkResponseState<List<StandingModel>>>  = flow {
        emit(NetworkResponseState.Loading)
        when(val response = remoteDataSource.getMatchesTable(code)){
            is NetworkResponseState.Success->{
                emit(NetworkResponseState.Success(response.result?.standings?.toStandingModel()))
            }
            is NetworkResponseState.Error->{
                emit(NetworkResponseState.Error(response.exception))
            }
            else->{}
        }
    }.flowOn(Dispatchers.IO)


}