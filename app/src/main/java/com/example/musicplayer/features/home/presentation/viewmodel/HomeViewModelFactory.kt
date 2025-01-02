package com.example.musicplayer.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.core.service.MusicService

class HomeViewModelFactory(private val musicService: MusicService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(musicService) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}