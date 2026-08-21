package com.dtbuddy.app.heroes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MatchHeroSelectionViewModel : ViewModel() {
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
}
