package com.example.goalplus.data.dto

data class MatchesTableDto(
    val area: AreaX?,
    val competition: CompetitionXX?,
    val filters: Filters?,
    val season: SeasonX?,
    val standings: List<Standing>?
)