package com.example.musicplayer.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicplayer.core.model.Favorite

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}