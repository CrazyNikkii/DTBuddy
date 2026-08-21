package com.dtbuddy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModel
import com.dtbuddy.app.heroes.HeroSelectionScreen
import com.dtbuddy.app.heroes.MatchHeroSelectionViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as DTBuddyApplication
        val matchHeroSelectionViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MatchHeroSelectionViewModel::class.java)) {
                    return MatchHeroSelectionViewModel(app.localMatchRepository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        })[MatchHeroSelectionViewModel::class.java]
        setContent {
            MaterialTheme {
                HeroSelectionScreen(matchHeroSelectionViewModel)
            }
        }
    }
}
