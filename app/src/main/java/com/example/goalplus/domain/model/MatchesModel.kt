package com.example.goalplus.domain.model

import com.example.goalplus.data.dto.AwayTeam
import com.example.goalplus.data.dto.CompetitionX
import com.example.goalplus.data.dto.HomeTeam
import com.example.goalplus.data.dto.Score
import com.example.goalplus.data.dto.Season

data class MatchesModel (
    val id:Int?,
    val matchDay:Int?,
    val status:String?,
    val awayTeam: AwayTeam?,
    val homeTeam: HomeTeam?,
    val score: Score?,
    val utcDate:String?,
    val competitionX: CompetitionX?,
    val season: Season?,
)

