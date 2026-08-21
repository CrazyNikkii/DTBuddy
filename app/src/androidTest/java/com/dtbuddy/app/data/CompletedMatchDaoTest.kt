package com.dtbuddy.app.data

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CompletedMatchDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var dao: CompletedMatchDao

    @Before
    fun setUp() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = database.completedMatchDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun historyOrdersByPlayedDateThenSaveTimeThenId() = runBlocking {
        val earlier = dao.insert(match(datePlayed = "2026-08-20", createdAtMillis = 20L))
        val sameTimeFirst = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 30L))
        val sameTimeSecond = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 30L))

        assertEquals(
            listOf(sameTimeSecond, sameTimeFirst, earlier),
            dao.getHistory().map { it.id },
        )
    }

    private fun match(datePlayed: String, createdAtMillis: Long) = CompletedMatchEntity(
        playerHeroName = "Barbarian",
        opponentHeroName = "Moon Elf",
        winner = "Player",
        firstPlayer = "Opponent",
        datePlayed = datePlayed,
        createdAtMillis = createdAtMillis,
    )
}
