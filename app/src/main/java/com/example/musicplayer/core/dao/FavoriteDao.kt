package com.example.musicplayer.core.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicplayer.core.model.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_table")
    suspend fun getFavorites(): List<Favorite>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_table WHERE songId = :id)")
    suspend fun isFavorite(id: Long): Boolean
}