package com.example.goalplus.data.api

import com.example.goalplus.common.API_KEY
import com.example.goalplus.data.dto.CompetitionsDto
import com.example.goalplus.data.dto.MatchesDto
import com.example.goalplus.data.dto.MatchesTableDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("competitions")
    suspend fun getCompetitions(
//        @Header("X-Auth-Token") token: String = API_KEY
    ): Response<CompetitionsDto>

    @GET("competitions/{code}/matches")
    suspend fun getMatches(
//        @Header("X-Auth-Token") token: String = API_KEY,
        @Path("code") code: String
    ): Response<MatchesDto>

    @GET("competitions/{code}/standings")
    suspend fun getMatchesTable(
//        @Header("X-Auth-Token") token: String = API_KEY,
        @Path("code") code: String
    ): Response<MatchesTableDto>
}