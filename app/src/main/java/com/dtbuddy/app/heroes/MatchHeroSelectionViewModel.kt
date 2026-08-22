package com.dtbuddy.app.heroes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dtbuddy.app.data.CompletedMatchDraft
import com.dtbuddy.app.data.LocalMatchRepository
import java.time.LocalDate

class MatchHeroSelectionViewModel(
    private val localMatchRepository: LocalMatchRepository,
) : ViewModel() {
    var state by mutableStateOf(MatchHeroSelectionState())
        private set

    fun selectPlayer(hero: Hero) {
        state = state.selectPlayer(hero)
    }

    fun selectOpponent(hero: Hero) {
        state = state.selectOpponent(hero)
    }

    fun selectWinner(participant: MatchParticipant) {
        state = state.selectWinner(participant)
    }

    fun selectFirstPlayer(participant: MatchParticipant) {
        state = state.selectFirstPlayer(participant)
    }

    fun selectDatePlayed(date: LocalDate) {
        state = state.selectDatePlayed(date)
    }

    fun ensureDatePlayed(today: LocalDate) {
        if (state.datePlayed == null) {
            selectDatePlayed(today)
        }
    }

    suspend fun saveMatch(): Boolean {
        if (state.isSaving) return false

        val draft = state.completedMatchDraftOrNull() ?: return false
        state = state.copy(isSaving = true)
        return try {
            localMatchRepository.save(draft)
            true
        } finally {
            state = state.copy(isSaving = false)
        }
    }

    suspend fun loadHistory() {
        state = state.copy(
            historyMatches = localMatchRepository.getHistory(),
            hasLoadedHistory = true,
        )
    }

    fun requestMatchDeletion(match: com.dtbuddy.app.data.CompletedMatchEntity) {
        state = state.copy(pendingDeletionMatch = match)
    }

    fun cancelMatchDeletion() {
        state = state.copy(pendingDeletionMatch = null)
    }

    suspend fun confirmMatchDeletion(): Boolean {
        val match = state.pendingDeletionMatch ?: return false
        val deleted = localMatchRepository.delete(match.id)
        state = state.copy(pendingDeletionMatch = null)
        if (deleted) {
            loadHistory()
        }
        return deleted
    }

    suspend fun loadPersonalOverallStats() {
        state = state.copy(
            personalOverallStats = localMatchRepository.getPersonalOverallStats(),
        )
    }

    suspend fun loadPersonalHeroStats() {
        state = state.copy(
            personalHeroStats = localMatchRepository.getPersonalHeroStats(),
        )
    }

    suspend fun loadPersonalHeroTurnOrderDetail(heroName: String) {
        state = state.copy(
            personalHeroTurnOrderDetail = localMatchRepository.getPersonalHeroTurnOrderDetail(heroName),
        )
    }

    fun startNewMatch() {
        state = MatchHeroSelectionState()
    }
}
