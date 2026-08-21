package com.dtbuddy.app.heroes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController

private const val playerHeroRoute = "playerHero"
private const val opponentHeroRoute = "opponentHero"
private const val winnerRoute = "winner"
private const val firstPlayerRoute = "firstPlayer"
private const val summaryRoute = "summary"

@Composable
fun HeroSelectionScreen(viewModel: MatchHeroSelectionViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = playerHeroRoute) {
        composable(playerHeroRoute) {
            HeroPicker(
                title = "Choose your hero",
                description = "Start your match log by choosing the hero you played.",
                selectedHeroName = viewModel.state.playerHeroName,
                onHeroSelected = {
                    viewModel.selectPlayer(it)
                    navController.navigate(opponentHeroRoute)
                },
            )
        }
        composable(opponentHeroRoute) {
            val playerHeroName = viewModel.state.playerHeroName
            if (playerHeroName == null) {
                ReturnToPlayerHeroStep(navController)
            } else {
                HeroPicker(
                    title = "Choose opponent hero",
                    description = "You chose $playerHeroName. Now choose the opponent's hero.",
                    selectedHeroName = null,
                    onHeroSelected = {
                        viewModel.selectOpponent(it)
                        navController.navigate(winnerRoute)
                    },
                    onBack = navController::popBackStack,
                )
            }
        }
        composable(winnerRoute) {
            val state = viewModel.state
            if (state.playerHeroName == null || state.opponentHeroName == null) {
                ReturnToPlayerHeroStep(navController)
            } else {
                ParticipantChoice(
                    title = "Who won?",
                    description = "Choose the winner of this completed match.",
                    playerHeroName = state.playerHeroName,
                    opponentHeroName = state.opponentHeroName,
                    playerChoiceLabel = "You won",
                    opponentChoiceLabel = "Opponent won",
                    onPlayerChosen = {
                        viewModel.selectWinner(MatchParticipant.Player)
                        navController.navigate(firstPlayerRoute)
                    },
                    onOpponentChosen = {
                        viewModel.selectWinner(MatchParticipant.Opponent)
                        navController.navigate(firstPlayerRoute)
                    },
                    onBack = navController::popBackStack,
                )
            }
        }
        composable(firstPlayerRoute) {
            val state = viewModel.state
            if (state.playerHeroName == null || state.opponentHeroName == null || state.winner == null) {
                ReturnToPlayerHeroStep(navController)
            } else {
                ParticipantChoice(
                    title = "Who went first?",
                    description = "Choose which player took the first turn.",
                    playerHeroName = state.playerHeroName,
                    opponentHeroName = state.opponentHeroName,
                    winnerText = "Winner: ${participantLabel(state.winner, state.playerHeroName, state.opponentHeroName)}",
                    playerChoiceLabel = "You went first",
                    opponentChoiceLabel = "Opponent went first",
                    onPlayerChosen = {
                        viewModel.selectFirstPlayer(MatchParticipant.Player)
                        navController.navigate(summaryRoute)
                    },
                    onOpponentChosen = {
                        viewModel.selectFirstPlayer(MatchParticipant.Opponent)
                        navController.navigate(summaryRoute)
                    },
                    onBack = navController::popBackStack,
                )
            }
        }
        composable(summaryRoute) {
            val state = viewModel.state
            if (
                state.playerHeroName == null ||
                state.opponentHeroName == null ||
                state.winner == null ||
                state.firstPlayer == null
            ) {
                ReturnToPlayerHeroStep(navController)
            } else {
                MatchSummary(
                    playerHeroName = state.playerHeroName,
                    opponentHeroName = state.opponentHeroName,
                    winner = state.winner,
                    firstPlayer = state.firstPlayer,
                    onBack = navController::popBackStack,
                )
            }
        }
    }
}

@Composable
private fun ReturnToPlayerHeroStep(navController: NavHostController) {
    LaunchedEffect(Unit) {
        if (!navController.popBackStack(playerHeroRoute, inclusive = false)) {
            navController.navigate(playerHeroRoute) {
                launchSingleTop = true
            }
        }
    }
}

@Composable
private fun HeroPicker(
    title: String,
    description: String,
    selectedHeroName: String?,
    onHeroSelected: (Hero) -> Unit,
    onBack: (() -> Unit)? = null,
) {
    var query by rememberSaveable(title) { mutableStateOf("") }
    val heroes = HeroCatalog.search(query)
    val isSearching = query.isNotBlank()

    Column(modifier = Modifier.fillMaxSize()) {
        if (onBack != null) {
            Button(
                onClick = onBack,
                modifier = Modifier.padding(start = 24.dp, top = 16.dp),
            ) {
                Text("Back")
            }
        }
        Text(
            text = title,
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = description,
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        if (selectedHeroName != null) {
            Text(
                text = "Selected: $selectedHeroName",
                modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            label = { Text("Search heroes") },
            singleLine = true,
        )

        if (isSearching && heroes.isEmpty()) {
            Text(
                text = "No heroes match \"${query.trim()}\".",
                modifier = Modifier.padding(horizontal = 24.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        } else {
            HeroList(
                heroes = heroes,
                groupResults = !isSearching,
                selectedHeroName = selectedHeroName,
                onHeroSelected = onHeroSelected,
            )
        }
    }
}

@Composable
private fun ParticipantChoice(
    title: String,
    description: String,
    playerHeroName: String,
    opponentHeroName: String,
    playerChoiceLabel: String,
    opponentChoiceLabel: String,
    onPlayerChosen: () -> Unit,
    onOpponentChosen: () -> Unit,
    onBack: () -> Unit,
    winnerText: String? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(description, style = MaterialTheme.typography.bodyLarge)
        Text("Your hero: $playerHeroName", style = MaterialTheme.typography.bodyLarge)
        Text("Opponent hero: $opponentHeroName", style = MaterialTheme.typography.bodyLarge)
        if (winnerText != null) {
            Text(winnerText, style = MaterialTheme.typography.bodyLarge)
        }
        Button(onClick = onPlayerChosen, modifier = Modifier.fillMaxWidth()) {
            Text(playerChoiceLabel)
        }
        Button(onClick = onOpponentChosen, modifier = Modifier.fillMaxWidth()) {
            Text(opponentChoiceLabel)
        }
    }
}

@Composable
private fun MatchSummary(
    playerHeroName: String,
    opponentHeroName: String,
    winner: MatchParticipant,
    firstPlayer: MatchParticipant,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }
        Text("Match details chosen", style = MaterialTheme.typography.headlineMedium)
        Text("Your hero: $playerHeroName", style = MaterialTheme.typography.bodyLarge)
        Text("Opponent hero: $opponentHeroName", style = MaterialTheme.typography.bodyLarge)
        Text(
            "Winner: ${participantLabel(winner, playerHeroName, opponentHeroName)}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            "First player: ${participantLabel(firstPlayer, playerHeroName, opponentHeroName)}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            "Date selection and saving will be added in a later step.",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

private fun participantLabel(
    participant: MatchParticipant,
    playerHeroName: String,
    opponentHeroName: String,
): String = when (participant) {
    MatchParticipant.Player -> "You ($playerHeroName)"
    MatchParticipant.Opponent -> "Opponent ($opponentHeroName)"
}

@Composable
private fun HeroList(
    heroes: List<Hero>,
    groupResults: Boolean,
    selectedHeroName: String?,
    onHeroSelected: (Hero) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (groupResults) {
            HeroGroup.entries.forEach { group ->
                val heroesInGroup = heroes.filter { it.group == group }
                item(key = group.name) {
                    Text(
                        text = group.displayName,
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 4.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
                items(heroesInGroup, key = { it.name }) { hero ->
                    HeroRow(hero, hero.name == selectedHeroName, onHeroSelected)
                }
            }
        } else {
            items(heroes, key = { it.name }) { hero ->
                HeroRow(hero, hero.name == selectedHeroName, onHeroSelected)
            }
        }
    }
}

@Composable
private fun HeroRow(
    hero: Hero,
    isSelected: Boolean,
    onHeroSelected: (Hero) -> Unit,
) {
    Button(
        onClick = { onHeroSelected(hero) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(if (isSelected) "${hero.name} (selected)" else hero.name)
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
private fun HeroSelectionScreenPreview() {
    MaterialTheme {
        HeroSelectionScreen(MatchHeroSelectionViewModel())
    }
}
