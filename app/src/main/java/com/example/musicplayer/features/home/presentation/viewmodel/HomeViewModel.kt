package com.example.musicplayer.features.home.presentation.viewmodel


import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.core.service.MusicService

class HomeViewModel(private val musicService: MusicService): ViewModel() {

    val songs: State<List<Song>> = musicService.song

    val currentSong: State<Song?> = musicService.currentSong

    fun playSong(songId: Long) {
        musicService.playSong(songId)
    }

    fun pauseSong(){
        musicService.pauseSong()
    }

    fun resumeSong(){
        musicService.resumeSong()
    }

    fun moveSong(forward: Boolean){
        musicService.moveSong(forward)
    }


}