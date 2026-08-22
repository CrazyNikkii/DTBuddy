package com.dtbuddy.app.heroes

import com.dtbuddy.app.data.CompletedMatchEntity
import com.dtbuddy.app.data.PersonalOverallStats
import com.dtbuddy.app.data.PersonalHeroStats
import com.dtbuddy.app.data.PersonalHeroTurnOrderDetail
import java.time.LocalDate

enum class MatchParticipant {
    Player,
    Opponent,
}

data class MatchHeroSelectionState(
    val playerHeroName: String? = null,
    val opponentHeroName: String? = null,
    val winner: MatchParticipant? = null,
    val firstPlayer: MatchParticipant? = null,
    val datePlayed: LocalDate? = null,
    val note: String = "",
    val isSaving: Boolean = false,
    val historyMatches: List<CompletedMatchEntity> = emptyList(),
    val hasLoadedHistory: Boolean = false,
    val personalOverallStats: PersonalOverallStats = PersonalOverallStats(),
    val personalHeroStats: List<PersonalHeroStats> = emptyList(),
    val personalHeroTurnOrderDetail: PersonalHeroTurnOrderDetail? = null,
    val pendingDeletionMatch: CompletedMatchEntity? = null,
    val editingMatchId: Long? = null,
    val editReturnToProfileHistory: Boolean = false,
    val editingOriginalDatePlayed: LocalDate? = null,
    val lastSaveWasEdit: Boolean = false,
) {
    val isEditing: Boolean get() = editingMatchId != null

    fun selectPlayer(hero: Hero): MatchHeroSelectionState = if (isEditing) {
        copy(playerHeroName = hero.name)
    } else {
        copy(playerHeroName = hero.name, opponentHeroName = null, winner = null, firstPlayer = null, datePlayed = null)
    }

    fun selectOpponent(hero: Hero): MatchHeroSelectionState = if (isEditing) {
        copy(opponentHeroName = hero.name)
    } else {
        copy(opponentHeroName = hero.name, winner = null, firstPlayer = null, datePlayed = null)
    }

    fun selectWinner(participant: MatchParticipant): MatchHeroSelectionState = if (isEditing) {
        copy(winner = participant)
    } else {
        copy(winner = participant, firstPlayer = null, datePlayed = null)
    }

    fun selectFirstPlayer(participant: MatchParticipant): MatchHeroSelectionState = if (isEditing) {
        copy(firstPlayer = participant)
    } else {
        copy(firstPlayer = participant, datePlayed = null)
    }

    fun selectDatePlayed(date: LocalDate): MatchHeroSelectionState = copy(datePlayed = date)

    fun selectNote(note: String): MatchHeroSelectionState = copy(note = note.take(MAXIMUM_NOTE_LENGTH))

    fun startEditing(
        match: CompletedMatchEntity,
        returnToProfileHistory: Boolean = false,
    ): MatchHeroSelectionState = copy(
        playerHeroName = match.playerHeroName,
        opponentHeroName = match.opponentHeroName,
        winner = MatchParticipant.valueOf(match.winner),
        firstPlayer = MatchParticipant.valueOf(match.firstPlayer),
        datePlayed = LocalDate.parse(match.datePlayed),
        note = match.note.orEmpty(),
        editingMatchId = match.id,
        editReturnToProfileHistory = returnToProfileHistory,
        editingOriginalDatePlayed = LocalDate.parse(match.datePlayed),
        lastSaveWasEdit = false,
        pendingDeletionMatch = null,
    )

    fun discardEditing(): MatchHeroSelectionState = copy(
        playerHeroName = null,
        opponentHeroName = null,
        winner = null,
        firstPlayer = null,
        datePlayed = null,
        note = "",
        editingMatchId = null,
        editReturnToProfileHistory = false,
        editingOriginalDatePlayed = null,
        lastSaveWasEdit = false,
    )

    fun completedMatchDraftOrNull(): com.dtbuddy.app.data.CompletedMatchDraft? {
        val playerHero = playerHeroName ?: return null
        val opponentHero = opponentHeroName ?: return null
        val selectedWinner = winner ?: return null
        val selectedFirstPlayer = firstPlayer ?: return null
        val selectedDatePlayed = datePlayed ?: return null
        return com.dtbuddy.app.data.CompletedMatchDraft(
            playerHeroName = playerHero,
            opponentHeroName = opponentHero,
            winner = selectedWinner,
            firstPlayer = selectedFirstPlayer,
            datePlayed = selectedDatePlayed,
            note = note.trim().takeIf { it.isNotEmpty() },
        )
    }

    companion object {
        const val MAXIMUM_NOTE_LENGTH = 500
    }
}
