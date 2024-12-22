package com.example.musicplayer.features.home.domain.repository

import com.example.musicplayer.core.model.Song

interface SongRepository {
    fun getSongs(): List<Song>
}