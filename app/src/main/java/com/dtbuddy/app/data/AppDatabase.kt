package com.dtbuddy.app.data

import androidx.room.Database
import androidx.room.migration.Migration
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CompletedMatchEntity::class, FavouriteHeroEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun completedMatchDao(): CompletedMatchDao
    abstract fun favouriteHeroDao(): FavouriteHeroDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE completed_matches ADD COLUMN note TEXT")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS favourite_heroes (heroName TEXT NOT NULL, position INTEGER NOT NULL, PRIMARY KEY(heroName))",
                )
            }
        }
    }
}
