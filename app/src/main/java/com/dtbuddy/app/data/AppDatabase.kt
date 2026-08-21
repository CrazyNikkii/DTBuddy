package com.dtbuddy.app.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CompletedMatchEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun completedMatchDao(): CompletedMatchDao
}
