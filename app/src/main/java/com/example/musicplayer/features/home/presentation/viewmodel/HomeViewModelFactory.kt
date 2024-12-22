package com.example.musicplayer.features.home.presentation.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.features.home.data.repository.SongRepositoryImpl
import com.example.musicplayer.features.home.domain.usecase.GetSongsUseCase

class HomeViewModelFactory(
    private val contentResolver: ContentResolver,
    private val mediaPlayer: MediaPlayer?,
    private val context: Context
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val repository = SongRepositoryImpl(contentResolver = contentResolver)
            val useCase = GetSongsUseCase(repository)
            return HomeViewModel(
                useCase,
                mediaPlayer,
                context
            ) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}