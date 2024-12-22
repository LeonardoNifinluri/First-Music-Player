package com.example.musicplayer.features.playlist.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun PlaylistScreen(
    navController: NavController
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MainColor
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Playlist Screen",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}