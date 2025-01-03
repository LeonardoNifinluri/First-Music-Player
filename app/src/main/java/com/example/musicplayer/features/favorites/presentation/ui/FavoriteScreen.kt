package com.example.musicplayer.features.favorites.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.musicplayer.core.ui.navigation.AppRoutes
import com.example.musicplayer.core.ui.shared_component.Header
import com.example.musicplayer.core.ui.shared_component.PlayedSongCard
import com.example.musicplayer.core.ui.shared_component.SongListSection
import com.example.musicplayer.features.favorites.presentation.viewmodel.FavoriteViewModel
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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Header(
                title = "Favorite",
                onBackClick = {
                    navController.navigate(route = AppRoutes.Home.route){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            )
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
            SongListSection(
                title = "Play",
                songs = favoriteSongs,
                onPlayClick = {
                    Log.d("PlayFavoriteButton", "Clicked")
                    viewModel.playFavoriteSongs()
                },
                onAddToFavorite = { song ->
                    Log.d("FavoriteButton", "Clicked with id: $song")
                    if(song.isFavorite.value){
                        viewModel.removeFromFavorite(song)
                    }else{
                        viewModel.addToFavorite(song)
                    }
                },
                onSongClick = { songId ->
                    viewModel.playSong(songId = songId)
                },
                onSortClick = {
                    Log.d("SortButton", "Clicked")
                }
            )

        }
    }
}