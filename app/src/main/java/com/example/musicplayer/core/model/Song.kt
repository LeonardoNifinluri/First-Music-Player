package com.example.musicplayer.core.model

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Song(
    val id: Long,
    val name: String,
    val uri: Uri?,
    val isPlaying: MutableState<Boolean> = mutableStateOf(value = false),
)