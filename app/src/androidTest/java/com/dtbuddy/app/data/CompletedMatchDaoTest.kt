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
    private val legacyDatabaseName = "completed-match-v1-test.db"
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
        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase(legacyDatabaseName)
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

    @Test
    fun deleteByIdRemovesOnlyTheSelectedStoredMatch() = runBlocking {
        val first = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 10L))
        val selected = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 10L))
        val third = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 10L))

        assertEquals(1, dao.deleteById(selected))
        assertEquals(listOf(third, first), dao.getHistory().map { it.id })
    }

    @Test
    fun updateByIdReplacesOnlyTheSelectedStoredMatch() = runBlocking {
        val first = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 10L))
        val selected = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 20L))

        assertEquals(
            1,
            dao.updateById(
                id = selected,
                playerHeroName = "Moon Elf",
                opponentHeroName = "Loki",
                winner = "Opponent",
                firstPlayer = "Player",
                datePlayed = "2026-08-22",
                note = null,
            ),
        )

        assertEquals(2, dao.getHistory().size)
        assertEquals(
            CompletedMatchEntity(
                id = selected,
                playerHeroName = "Moon Elf",
                opponentHeroName = "Loki",
                winner = "Opponent",
                firstPlayer = "Player",
                datePlayed = "2026-08-22",
                createdAtMillis = 20L,
            ),
            dao.getHistory().first(),
        )
        assertEquals(first, dao.getHistory().last().id)
    }

    @Test
    fun insertAndUpdateStoreTheOptionalNote() = runBlocking {
        val id = dao.insert(match(datePlayed = "2026-08-21", createdAtMillis = 10L).copy(note = "Initial note"))

        assertEquals("Initial note", dao.getHistory().single().note)
        assertEquals(
            1,
            dao.updateById(
                id = id,
                playerHeroName = "Barbarian",
                opponentHeroName = "Moon Elf",
                winner = "Player",
                firstPlayer = "Opponent",
                datePlayed = "2026-08-21",
                note = "Updated note",
            ),
        )
        assertEquals("Updated note", dao.getHistory().single().note)
    }

    @Test
    fun migrationFromVersionOnePreservesExistingMatchesWithoutNotes() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase(legacyDatabaseName)
        val versionOneDatabase = context.openOrCreateDatabase(legacyDatabaseName, Context.MODE_PRIVATE, null)
        versionOneDatabase.execSQL(
            """
            CREATE TABLE completed_matches (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                playerHeroName TEXT NOT NULL,
                opponentHeroName TEXT NOT NULL,
                winner TEXT NOT NULL,
                firstPlayer TEXT NOT NULL,
                datePlayed TEXT NOT NULL,
                createdAtMillis INTEGER NOT NULL
            )
            """.trimIndent(),
        )
        versionOneDatabase.execSQL(
            """
            INSERT INTO completed_matches
            (playerHeroName, opponentHeroName, winner, firstPlayer, datePlayed, createdAtMillis)
            VALUES ('Barbarian', 'Moon Elf', 'Player', 'Opponent', '2026-08-21', 10)
            """.trimIndent(),
        )
        versionOneDatabase.version = 1
        versionOneDatabase.close()

        val migratedDatabase = Room.databaseBuilder(context, AppDatabase::class.java, legacyDatabaseName)
            .addMigrations(AppDatabase.MIGRATION_1_2, AppDatabase.MIGRATION_2_3)
            .build()

        val migratedMatch = migratedDatabase.completedMatchDao().getHistory().single()
        assertEquals("Barbarian", migratedMatch.playerHeroName)
        assertEquals("Moon Elf", migratedMatch.opponentHeroName)
        assertEquals("Player", migratedMatch.winner)
        assertEquals("Opponent", migratedMatch.firstPlayer)
        assertEquals("2026-08-21", migratedMatch.datePlayed)
        assertEquals(10L, migratedMatch.createdAtMillis)
        assertEquals(null, migratedMatch.note)
        assertEquals(emptyList<FavouriteHeroEntity>(), migratedDatabase.favouriteHeroDao().getAll())
        migratedDatabase.close()
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
