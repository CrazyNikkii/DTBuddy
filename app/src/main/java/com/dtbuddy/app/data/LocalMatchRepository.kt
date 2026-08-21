package com.dtbuddy.app.data

class LocalMatchRepository(
    private val completedMatchDao: CompletedMatchDao,
    private val currentTimeMillis: () -> Long = System::currentTimeMillis,
) {
    suspend fun save(draft: CompletedMatchDraft): Long = completedMatchDao.insert(
        CompletedMatchEntity(
            playerHeroName = draft.playerHeroName,
            opponentHeroName = draft.opponentHeroName,
            winner = draft.winner.name,
            firstPlayer = draft.firstPlayer.name,
            datePlayed = draft.datePlayed.toString(),
            createdAtMillis = currentTimeMillis(),
        ),
    )
}
