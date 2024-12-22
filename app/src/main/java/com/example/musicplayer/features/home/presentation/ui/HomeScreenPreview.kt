package com.example.musicplayer.features.home.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.features.home.presentation.ui.component.ButtonSelectionSection
import com.example.musicplayer.features.home.presentation.ui.component.HeaderSection
import com.example.musicplayer.features.home.presentation.ui.component.SongListSection
import com.example.musicplayer.ui.theme.MainColor

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val songs = listOf(
        Song(
            id = 122221L,
            name = "Third Song",
            uri = null
        ),
        Song(
            id = 122222L,
            name = "First Song",
            uri = null
        ),
        Song(
            id = 122223L,
            name = "Second Song",
            uri = null
        ),
        Song(
            id = 122224L,
            name = "Third Song",
            uri = null
        ),
        Song(
            id = 122225L,
            name = "Third Song",
            uri = null
        ),
        Song(
            id = 122226L,
            name = "Third Song",
            uri = null
        ),
        Song(
            id = 122227L,
            name = "Third Song",
            uri = null
        ),
        Song(
            id = 122228L,
            name = "Third Song",
            uri = null
        ),
        Song(
            id = 122229L,
            name = "Third Song",
            uri = null
        ),
    )

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
                    Log.d("SearchButton", "Clicked")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonSelectionSection(
                onFavoriteClick = {
                    Log.d("FavoriteButton", "Clicked")
                },
                onPlaylistClick = {
                    Log.d("PlaylistButton", "Clicked")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SongListSection(
                songs = songs,
                onShuffleClick = {
                    Log.d("ShuffleButton", "Clicked")
                },
                onSortClick = {
                    Log.d("SortButton", "Clicked")
                },
                onSongClick = { songId ->
                    Log.d("SongCard", "Play song with id: $songId")
                }
            )
        }
    }
}
