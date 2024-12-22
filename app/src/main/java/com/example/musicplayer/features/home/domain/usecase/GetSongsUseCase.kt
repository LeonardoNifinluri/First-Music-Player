package com.example.musicplayer.features.home.domain.usecase

import com.example.musicplayer.core.model.Song
import com.example.musicplayer.features.home.domain.repository.SongRepository

class GetSongsUseCase(private val repository: SongRepository) {
    operator fun invoke(): List<Song>{
        return repository.getSongs()
    }
}