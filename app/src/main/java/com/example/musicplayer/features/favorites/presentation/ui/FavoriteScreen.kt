package com.example.musicplayer.features.favorites.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicplayer.core.ui.shared_component.PlayedSongCard
import com.example.musicplayer.features.favorites.presentation.viewmodel.FavoriteViewModel
import com.example.musicplayer.features.home.presentation.ui.component.SongCard
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navController: NavController
) {
    val favoriteSongs by viewModel.favoriteSongs.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MainColor
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Favorite Screen",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn (
                modifier = Modifier.fillMaxWidth()
            ){
                items(favoriteSongs){ song ->
                    SongCard(
                        song = song,
                        onClick = {
                            viewModel.playSong(songId = song.id)
                        },
                        onAddToFavorite = {
                            if(song.isFavorite.value){
                                viewModel.removeFromFavorite(song)
                            }else{
                                viewModel.addToFavorite(song)
                            }
                        }
                    )
                }
            }
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