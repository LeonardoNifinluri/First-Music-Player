package com.example.musicplayer.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicplayer.core.dao.FavoriteDao
import com.example.musicplayer.core.model.Favorite

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}