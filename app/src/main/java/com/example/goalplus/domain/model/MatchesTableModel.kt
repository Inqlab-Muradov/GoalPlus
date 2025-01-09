package com.example.goalplus.domain.model

import com.example.goalplus.data.dto.Team

data class MatchesTableModel (
    val position:Int?,
    val team: Team?,
    val playedGames:Int?,
    val won:Int?,
    val lost:Int?,
    val draw:Int?,
    val points:Int?,
    val goalsFor:Int?,
    val goalDifferences:Int?,
    val goalAgainst:Int?
)
