package com.example.musicplayer.features.favorites.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.core.service.MusicService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(private val musicService: MusicService): ViewModel() {

    val currentSong = musicService.currentSong

    //this stateIn method is used to convert a Flow to StateFlow for managing state in ui
    val favoriteSongs = musicService.favoriteSong.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun moveSong(forward: Boolean){
        musicService.moveSong(forward)
    }

    fun resumeSong(){
        musicService.resumeSong()
    }

    fun pauseSong(){
        musicService.pauseSong()
    }

    //this is should be played using favoriteSong
    fun playSong(songId: Long){
        musicService.playSong(
            songId = songId,
            isShuffled = false
        )
    }

    fun addToFavorite(song: Song){
        musicService.addToFavorite(song)
    }

    fun removeFromFavorite(song: Song){
        musicService.removeFromFavorite(song)
    }

}