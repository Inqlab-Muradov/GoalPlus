package com.example.goalplus.domain.usecase

import com.example.goalplus.domain.repository.Repository
import javax.inject.Inject

class MatchesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(code:String) = repository.getMatches(code)
}