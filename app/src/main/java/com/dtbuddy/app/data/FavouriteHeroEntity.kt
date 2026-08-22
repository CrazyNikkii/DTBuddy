package com.dtbuddy.app.data

import androidx.room.Entity

@Entity(tableName = "favourite_heroes", primaryKeys = ["heroName"])
data class FavouriteHeroEntity(
    val heroName: String,
    val position: Int,
)
