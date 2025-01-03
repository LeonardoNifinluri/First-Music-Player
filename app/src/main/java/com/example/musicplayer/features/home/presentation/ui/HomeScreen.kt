package com.example.musicplayer.features.home.presentation.ui


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicplayer.core.ui.navigation.AppRoutes
import com.example.musicplayer.core.ui.shared_component.PlayedSongCard
import com.example.musicplayer.features.home.presentation.ui.component.ButtonSelectionSection
import com.example.musicplayer.features.home.presentation.ui.component.HeaderSection
import com.example.musicplayer.core.ui.shared_component.SongListSection
import com.example.musicplayer.features.home.presentation.viewmodel.HomeViewModel
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.updateFavorite()
    }

    val songs = viewModel.songs.value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MainColor
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            HeaderSection(
                onSearchClick = {
                    //this is handle search
                    Log.d("SearchButton", "Clicked")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonSelectionSection(
                onFavoriteClick = {
                    //this is handle navigation to favorite screen
                    navController.navigate(route = AppRoutes.Favorite.route)
                },
                onPlaylistClick = {
                    //this is handle navigation to playlist screen
                    Log.d("PlaylistButton", "Clicked")
                }
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
            Spacer(modifier = Modifier.height(8.dp))
            SongListSection(
                title = "Shuffle",
                songs = songs,
                onPlayClick = {
                    //this is handle shuffle play
                    viewModel.playShuffle()
                },
                onSortClick = {
                    //this is handle sort display
                    Log.d("SortButton", "Clicked")
                },
                onSongClick = { songId ->
                    //this is handle play clicked song
                    Toast.makeText(context, "Playing song", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(context, viewModel.songs.value.find { songId == it.id }?.isFavorite.toString(), Toast.LENGTH_SHORT).show()
                    viewModel.playSong(songId = songId)
                },
                onAddToFavorite = { song ->
                    if(song.isFavorite.value){
                        viewModel.removeFromFavorite(song)
                    }else{
                        viewModel.addToFavorite(song)
                    }
                }
            )

        }
    }
}