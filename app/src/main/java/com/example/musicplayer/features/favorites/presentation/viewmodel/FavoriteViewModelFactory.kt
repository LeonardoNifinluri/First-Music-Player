package com.example.musicplayer.features.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.core.service.MusicService

class FavoriteViewModelFactory(private val musicService: MusicService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(musicService) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}