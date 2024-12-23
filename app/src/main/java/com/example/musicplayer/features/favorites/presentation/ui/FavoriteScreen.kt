package com.example.musicplayer.features.favorites.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicplayer.core.ui.shared_component.PlayedSongCard
import com.example.musicplayer.features.favorites.presentation.viewmodel.FavoriteViewModel
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MainColor
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Favorite Screen",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            if(viewModel.currentSong.value != null){
                PlayedSongCard(
                    song = viewModel.currentSong.value,
                    onPrev = {
                        viewModel.moveSong(forward = false)
                    },
                    onResume = {
                        viewModel.resumeSong()
                    },
                    onPause = {
                        viewModel.pauseSong()
                    },
                    onNext = {
                        viewModel.moveSong(forward = true)
                    }
                )
            }
        }
    }
}