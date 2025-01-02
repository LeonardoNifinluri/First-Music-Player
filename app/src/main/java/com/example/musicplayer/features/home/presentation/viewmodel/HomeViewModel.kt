package com.example.musicplayer.features.home.presentation.viewmodel


import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.core.service.MusicService
import kotlinx.coroutines.launch

class HomeViewModel(private val musicService: MusicService): ViewModel() {

    val songs: State<List<Song>> = musicService.song

    val currentSong: State<Song?> = musicService.currentSong

    fun updateFavorite(){
        songs.value.forEach {
            favoriteSongs(it)
        }
    }

    fun playSong(songId: Long) {
        musicService.playSong(
            songId = songId,
            isShuffled = false
        )
    }

    fun playShuffle(){
        musicService.playShuffle()
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

    fun addToFavorite(song: Song){
        musicService.addToFavorite(song)
    }

    fun removeFromFavorite(song: Song){
        musicService.removeFromFavorite(song)
    }

    private fun favoriteSongs(song: Song){
        viewModelScope.launch {
            song.isFavorite.value = musicService.isFavorite(song.id)
        }
    }


}