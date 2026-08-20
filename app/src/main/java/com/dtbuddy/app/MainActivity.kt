package com.dtbuddy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.dtbuddy.app.heroes.HeroSelectionScreen
import com.dtbuddy.app.heroes.MatchHeroSelectionViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val matchHeroSelectionViewModel = ViewModelProvider(this)[MatchHeroSelectionViewModel::class.java]
        setContent {
            MaterialTheme {
                HeroSelectionScreen(matchHeroSelectionViewModel)
            }
        }
    }
}
