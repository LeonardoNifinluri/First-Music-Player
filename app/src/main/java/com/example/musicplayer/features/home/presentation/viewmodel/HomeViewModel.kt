package com.example.musicplayer.features.home.presentation.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.features.home.domain.usecase.GetSongsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getSongsUseCase: GetSongsUseCase,
    private var mediaPlayer: MediaPlayer?,
    private val context: Context
): ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs

    private val _currentSong = mutableStateOf<Song?>(value = null)
    val currentSong: State<Song?> = _currentSong

    fun fetchSongs(){
        viewModelScope.launch {
            _songs.value = getSongsUseCase()
        }
    }

    fun playSong(songId: Long) {
        // Stop any currently playing song
        stopSong()

        // Find the song by ID
        _songs.value.find { it.id == songId }?.let { song ->
            _currentSong.value = song
            song.isPlaying.value = true

            song.uri?.let { songUri ->
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(context, songUri)
                    prepare()
                    start()
                    //this is to track if the playback is done
                    setOnCompletionListener {
                        _currentSong.value?.isPlaying?.value = false
                        Log.d("MediaPlayer", "Playback completed for ${song.name}")
                    }
                }
            }
        }
    }

    fun pauseSong(){
        mediaPlayer?.let{
            if(it.isPlaying){
                it.pause()
                _currentSong.value?.isPlaying?.value = false
            }
        }
    }

    fun resumeSong(){
        mediaPlayer?.start()
        _currentSong.value?.isPlaying?.value = true
    }

    fun nextSong(){}

    fun prevSong(){}
    private fun stopSong(){
        //this is to update all the songs isPlaying state
        _songs.value.find { song ->
            song.isPlaying.value
        }?.let {
            it.isPlaying.value = false
        }
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }


}