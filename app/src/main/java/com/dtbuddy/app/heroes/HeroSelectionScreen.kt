package com.dtbuddy.app.heroes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeroSelectionScreen() {
    var query by rememberSaveable { mutableStateOf("") }
    val heroes = HeroCatalog.search(query)
    val isSearching = query.isNotBlank()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Choose a hero",
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = "Browse the DTBuddy roster or search by name.",
            modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
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
            HeroList(heroes = heroes, groupResults = !isSearching)
        }
    }
}

@Composable
private fun HeroList(
    heroes: List<Hero>,
    groupResults: Boolean,
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
                items(heroesInGroup, key = { it.name }) { hero -> HeroRow(hero) }
            }
        } else {
            items(heroes, key = { it.name }) { hero -> HeroRow(hero) }
        }
    }
}

@Composable
private fun HeroRow(hero: Hero) {
    Text(
        text = hero.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        style = MaterialTheme.typography.bodyLarge,
    )
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
private fun HeroSelectionScreenPreview() {
    MaterialTheme {
        HeroSelectionScreen()
    }
}
