package com.dtbuddy.app.heroes

import android.app.DatePickerDialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.dtbuddy.app.data.CompletedMatchEntity
import com.dtbuddy.app.data.PersonalHeroMatchupStats
import com.dtbuddy.app.data.PersonalHeroStats
import com.dtbuddy.app.data.PersonalHeroTurnOrderDetail
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlinx.coroutines.launch

private const val playerHeroRoute = "playerHero"
private const val opponentHeroRoute = "opponentHero"
private const val winnerRoute = "winner"
private const val firstPlayerRoute = "firstPlayer"
private const val datePlayedRoute = "datePlayed"
private const val summaryRoute = "summary"
private const val savedMatchRoute = "savedMatch"
private const val matchHistoryRoute = "matchHistory"

private enum class MainDestination(val label: String) {
    LogMatch("Log match"),
    Requests("Requests"),
    Profile("Profile"),
    GlobalStats("Global stats"),
}

private enum class ProfilePage {
    Overview,
    Heroes,
    HeroDetail,
    History,
}

@Composable
fun HeroSelectionScreen(viewModel: MatchHeroSelectionViewModel) {
    var selectedDestinationName by remember { mutableStateOf(MainDestination.LogMatch.name) }
    val selectedDestination = MainDestination.valueOf(selectedDestinationName)

    Scaffold(
        bottomBar = {
            NavigationBar {
                MainDestination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = selectedDestination == destination,
                        onClick = { selectedDestinationName = destination.name },
                        icon = { Text(destination.label) },
                        alwaysShowLabel = false,
                    )
                }
            }
        },
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (selectedDestination) {
                MainDestination.LogMatch -> MatchLogFlow(viewModel)
                MainDestination.Requests -> RequestsPlaceholder()
                MainDestination.Profile -> ProfileDestination(viewModel)
                MainDestination.GlobalStats -> GlobalStatsPlaceholder()
            }
        }
    }
}

@Composable
private fun MatchLogFlow(viewModel: MatchHeroSelectionViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

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
                        viewModel.ensureDatePlayed(LocalDate.now())
                        navController.navigate(datePlayedRoute)
                    },
                    onOpponentChosen = {
                        viewModel.selectFirstPlayer(MatchParticipant.Opponent)
                        viewModel.ensureDatePlayed(LocalDate.now())
                        navController.navigate(datePlayedRoute)
                    },
                    onBack = navController::popBackStack,
                )
            }
        }
        composable(datePlayedRoute) {
            val state = viewModel.state
            if (
                state.playerHeroName == null ||
                state.opponentHeroName == null ||
                state.winner == null ||
                state.firstPlayer == null
            ) {
                ReturnToPlayerHeroStep(navController)
            } else {
                DatePlayedChoice(
                    datePlayed = state.datePlayed ?: LocalDate.now(),
                    onDateSelected = viewModel::selectDatePlayed,
                    onContinue = { navController.navigate(summaryRoute) },
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
                state.firstPlayer == null ||
                state.datePlayed == null
            ) {
                ReturnToPlayerHeroStep(navController)
            } else {
                MatchSummary(
                    playerHeroName = state.playerHeroName,
                    opponentHeroName = state.opponentHeroName,
                    winner = state.winner,
                    firstPlayer = state.firstPlayer,
                    datePlayed = state.datePlayed,
                    isSaving = state.isSaving,
                    onSave = {
                        coroutineScope.launch {
                            if (viewModel.saveMatch()) {
                                navController.navigate(savedMatchRoute) {
                                    popUpTo(summaryRoute) { inclusive = true }
                                }
                            }
                        }
                    },
                    onBack = navController::popBackStack,
                )
            }
        }
        composable(savedMatchRoute) {
            if (viewModel.state.completedMatchDraftOrNull() == null) {
                ReturnToPlayerHeroStep(navController)
            } else {
                SavedMatchConfirmation(
                    onViewMatchHistory = { navController.navigate(matchHistoryRoute) },
                    onLogAnotherMatch = {
                        viewModel.startNewMatch()
                        navController.navigate(playerHeroRoute) {
                            popUpTo(playerHeroRoute) { inclusive = true }
                        }
                    },
                )
            }
        }
        composable(matchHistoryRoute) {
            LaunchedEffect(Unit) {
                viewModel.loadHistory()
            }
            MatchHistory(
                matches = viewModel.state.historyMatches,
                hasLoadedHistory = viewModel.state.hasLoadedHistory,
                onBack = navController::popBackStack,
            )
        }
    }
}

@Composable
private fun DatePlayedChoice(
    datePlayed: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val formattedDate = datePlayed.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }
        Text("When was the match played?", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Today is selected by default. Choose another date if needed.",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text("Date played: $formattedDate", style = MaterialTheme.typography.titleMedium)
        Button(
            onClick = {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
                    },
                    datePlayed.year,
                    datePlayed.monthValue - 1,
                    datePlayed.dayOfMonth,
                ).show()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Choose another date")
        }
        Button(onClick = onContinue, modifier = Modifier.fillMaxWidth()) {
            Text("Continue")
        }
    }
}

@Composable
private fun RequestsPlaceholder() {
    PlaceholderDestination(
        title = "Requests",
        message = "Linked-match requests will be available in a later milestone. They are not part of this local solo test.",
    )
}

@Composable
private fun GlobalStatsPlaceholder() {
    PlaceholderDestination(
        title = "Global stats",
        message = "Community statistics are not available yet. This local solo test keeps your saved matches on this device.",
    )
}

@Composable
private fun PlaceholderDestination(title: String, message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(message, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun ProfileDestination(viewModel: MatchHeroSelectionViewModel) {
    var profilePageName by rememberSaveable { mutableStateOf(ProfilePage.Overview.name) }
    var selectedHeroName by rememberSaveable { mutableStateOf<String?>(null) }
    val profilePage = ProfilePage.valueOf(profilePageName)

    when (profilePage) {
        ProfilePage.History -> {
            BackHandler { profilePageName = ProfilePage.Overview.name }
            LaunchedEffect(Unit) {
                viewModel.loadHistory()
            }
            MatchHistory(
                matches = viewModel.state.historyMatches,
                hasLoadedHistory = viewModel.state.hasLoadedHistory,
                onBack = { profilePageName = ProfilePage.Overview.name },
            )
        }
        ProfilePage.Heroes -> {
            BackHandler { profilePageName = ProfilePage.Overview.name }
            LaunchedEffect(Unit) {
                viewModel.loadPersonalHeroStats()
            }
            PersonalHeroes(
                stats = viewModel.state.personalHeroStats,
                onBack = { profilePageName = ProfilePage.Overview.name },
                onHeroSelected = { heroName ->
                    selectedHeroName = heroName
                    profilePageName = ProfilePage.HeroDetail.name
                },
            )
        }
        ProfilePage.HeroDetail -> {
            val heroName = selectedHeroName
            if (heroName == null) {
                LaunchedEffect(Unit) {
                    profilePageName = ProfilePage.Heroes.name
                }
            } else {
                BackHandler { profilePageName = ProfilePage.Heroes.name }
                LaunchedEffect(heroName) {
                    viewModel.loadPersonalHeroTurnOrderDetail(heroName)
                }
                val detail = viewModel.state.personalHeroTurnOrderDetail
                if (detail?.heroName == heroName) {
                    PersonalHeroTurnOrderDetailScreen(
                        detail = detail,
                        onBack = { profilePageName = ProfilePage.Heroes.name },
                    )
                }
            }
        }
        ProfilePage.Overview -> {
            LaunchedEffect(Unit) {
                viewModel.loadPersonalOverallStats()
            }
            val stats = viewModel.state.personalOverallStats
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text("My profile", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "During this solo test, your saved matches stay on this device.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text("Personal overall", style = MaterialTheme.typography.titleLarge)
                Text("Games played: ${stats.gamesPlayed}", style = MaterialTheme.typography.bodyLarge)
                Text("Wins: ${stats.wins}", style = MaterialTheme.typography.bodyLarge)
                Text("Losses: ${stats.losses}", style = MaterialTheme.typography.bodyLarge)
                Text("Win rate: ${stats.winRatePercentage}%", style = MaterialTheme.typography.bodyLarge)
                Button(
                    onClick = { profilePageName = ProfilePage.Heroes.name },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Heroes")
                }
                Button(
                    onClick = { profilePageName = ProfilePage.History.name },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Match history")
                }
            }
        }
    }
}

@Composable
private fun PersonalHeroes(
    stats: List<PersonalHeroStats>,
    onBack: () -> Unit,
    onHeroSelected: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = onBack,
            modifier = Modifier.padding(start = 24.dp, top = 16.dp),
        ) {
            Text("Back")
        }
        Text(
            text = "Heroes",
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
        if (stats.isEmpty()) {
            Text(
                text = "No heroes have been played yet.",
                modifier = Modifier.padding(24.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(stats, key = { it.heroName }) { heroStats ->
                    PersonalHeroStatsRow(heroStats, onClick = { onHeroSelected(heroStats.heroName) })
                }
            }
        }
    }
}

@Composable
private fun PersonalHeroStatsRow(stats: PersonalHeroStats, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
            Text(stats.heroName)
        }
        Text("Games played: ${stats.gamesPlayed}", style = MaterialTheme.typography.bodyLarge)
        Text("Wins: ${stats.wins}", style = MaterialTheme.typography.bodyLarge)
        Text("Losses: ${stats.losses}", style = MaterialTheme.typography.bodyLarge)
        Text("Win rate: ${stats.winRatePercentage}%", style = MaterialTheme.typography.bodyLarge)
    }
    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
}

@Composable
private fun PersonalHeroTurnOrderDetailScreen(
    detail: PersonalHeroTurnOrderDetail,
    onBack: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Button(onClick = onBack) {
                Text("Back")
            }
        }
        item {
            Text(detail.heroName, style = MaterialTheme.typography.headlineMedium)
        }
        item {
            PersonalTurnOrderStatsSection(
                "Overall",
                detail.overall.gamesPlayed,
                detail.overall.wins,
                detail.overall.losses,
                detail.overall.winRatePercentage,
            )
        }
        item {
            PersonalTurnOrderStatsSection(
                "You went first",
                detail.playerWentFirst.gamesPlayed,
                detail.playerWentFirst.wins,
                detail.playerWentFirst.losses,
                detail.playerWentFirst.winRatePercentage,
            )
        }
        item {
            PersonalTurnOrderStatsSection(
                "Opponent went first",
                detail.opponentWentFirst.gamesPlayed,
                detail.opponentWentFirst.wins,
                detail.opponentWentFirst.losses,
                detail.opponentWentFirst.winRatePercentage,
            )
        }
        item {
            Text("Matchups", style = MaterialTheme.typography.titleLarge)
        }
        items(detail.matchups, key = { it.opponentHeroName }) { matchup ->
            PersonalHeroMatchupStatsRow(matchup)
        }
    }
}

@Composable
private fun PersonalHeroMatchupStatsRow(stats: PersonalHeroMatchupStats) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(stats.opponentHeroName, style = MaterialTheme.typography.titleMedium)
        Text("Games played: ${stats.gamesPlayed}", style = MaterialTheme.typography.bodyLarge)
        Text("Wins: ${stats.wins}", style = MaterialTheme.typography.bodyLarge)
        Text("Losses: ${stats.losses}", style = MaterialTheme.typography.bodyLarge)
        Text("Win rate: ${stats.winRatePercentage}%", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun PersonalTurnOrderStatsSection(
    title: String,
    gamesPlayed: Int,
    wins: Int,
    losses: Int,
    winRatePercentage: Int,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Text("Games played: $gamesPlayed", style = MaterialTheme.typography.bodyLarge)
        Text("Wins: $wins", style = MaterialTheme.typography.bodyLarge)
        Text("Losses: $losses", style = MaterialTheme.typography.bodyLarge)
        Text("Win rate: $winRatePercentage%", style = MaterialTheme.typography.bodyLarge)
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
    datePlayed: LocalDate,
    isSaving: Boolean,
    onSave: () -> Unit,
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
            "Date played: ${datePlayed.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            "Check the details, then save this completed match on your device.",
            style = MaterialTheme.typography.bodyLarge,
        )
        Button(
            onClick = onSave,
            enabled = !isSaving,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (isSaving) "Saving match…" else "Save match")
        }
    }
}

@Composable
private fun SavedMatchConfirmation(
    onViewMatchHistory: () -> Unit,
    onLogAnotherMatch: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Match saved", style = MaterialTheme.typography.headlineMedium)
        Text(
            "This completed match is saved on this device.",
            style = MaterialTheme.typography.bodyLarge,
        )
        Button(onClick = onViewMatchHistory, modifier = Modifier.fillMaxWidth()) {
            Text("View match history")
        }
        Button(onClick = onLogAnotherMatch, modifier = Modifier.fillMaxWidth()) {
            Text("Log another match")
        }
    }
}

@Composable
private fun MatchHistory(
    matches: List<CompletedMatchEntity>,
    hasLoadedHistory: Boolean,
    onBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = onBack,
            modifier = Modifier.padding(start = 24.dp, top = 16.dp),
        ) {
            Text("Back")
        }
        Text(
            text = "Match history",
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
        if (hasLoadedHistory && matches.isEmpty()) {
            Text(
                text = "No matches have been saved yet.",
                modifier = Modifier.padding(24.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(matches, key = { it.id }) { match ->
                    MatchHistoryRow(match)
                }
            }
        }
    }
}

@Composable
private fun MatchHistoryRow(match: CompletedMatchEntity) {
    val datePlayed = LocalDate.parse(match.datePlayed)
        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    val result = if (match.winner == MatchParticipant.Player.name) "Won" else "Lost"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(datePlayed, style = MaterialTheme.typography.titleMedium)
        Text("Your hero: ${match.playerHeroName}", style = MaterialTheme.typography.bodyLarge)
        Text("Opponent hero: ${match.opponentHeroName}", style = MaterialTheme.typography.bodyLarge)
        Text(result, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
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
