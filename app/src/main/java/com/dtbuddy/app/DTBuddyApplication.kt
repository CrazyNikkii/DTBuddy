package com.dtbuddy.app

import android.app.Application
import androidx.room.Room
import com.dtbuddy.app.data.AppDatabase
import com.dtbuddy.app.data.LocalMatchRepository

class DTBuddyApplication : Application() {
    val localMatchRepository: LocalMatchRepository by lazy {
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "dtbuddy.db",
        ).build()
        LocalMatchRepository(database.completedMatchDao())
    }
}
