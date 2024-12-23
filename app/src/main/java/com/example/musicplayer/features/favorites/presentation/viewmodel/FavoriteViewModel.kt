package com.example.musicplayer.features.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.musicplayer.core.service.MusicService

class FavoriteViewModel(private val musicService: MusicService): ViewModel() {

    val currentSong = musicService.currentSong

    fun moveSong(forward: Boolean){
        musicService.moveSong(forward)
    }

    fun resumeSong(){
        musicService.resumeSong()
    }

    fun pauseSong(){
        musicService.pauseSong()
    }

}