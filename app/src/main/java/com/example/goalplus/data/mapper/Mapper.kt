package com.example.goalplus.data.mapper

import com.example.goalplus.data.dto.Competition
import com.example.goalplus.data.dto.Matches
import com.example.goalplus.data.dto.Standing
import com.example.goalplus.data.dto.Table
import com.example.goalplus.domain.model.CompetitionModel
import com.example.goalplus.domain.model.MatchesModel
import com.example.goalplus.domain.model.MatchesTableModel
import com.example.goalplus.domain.model.StandingModel


fun List<Competition>.toCompetitionModel() = map {
    CompetitionModel(
        id = it.id,
        name= it.name,
        emblem = it.emblem,
        code = it.code
    )
}

fun List<Matches>.toMatchesModel()= map {
    MatchesModel(
        id = it.id,
        matchDay = it.matchday,
        competitionX = it.competition,
        status = it.status,
        utcDate = it.utcDate,
        homeTeam = it.homeTeam,
        awayTeam = it.awayTeam,
        score = it.score,
        season = it.season
    )
}

fun List<Table>.toMatchesTableModel() = map{
    MatchesTableModel(
        position = it.position,
        team = it.team,
        playedGames = it.playedGames,
        won = it.won,
        lost = it.lost,
        draw = it.draw,
        points = it.points,
        goalsFor = it.goalsFor,
        goalAgainst = it.goalsAgainst,
        goalDifferences = it.goalDifference
    )
}

fun List<Standing>.toStandingModel() = map {
    StandingModel(
        table = it.table
    )
}