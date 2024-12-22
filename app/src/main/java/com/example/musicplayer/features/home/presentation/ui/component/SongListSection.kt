package com.example.musicplayer.features.home.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun SongListSection(
    songs: List<Song>,
    onShuffleClick: () -> Unit,
    onSortClick: () -> Unit,
    onSongClick: (Long) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MainColor
    ) {
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            //this is for the header
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(
                        onClick = onShuffleClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Shuffle Button",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = "Shuffle",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                IconButton(
                    onClick = onSortClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Sort Button",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            HorizontalDivider(
                color = Color.White,
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                items(songs){ song ->
                    SongCard(
                        song = song,
                        onClick = {
                            onSongClick(song.id)
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSongListSection() {
    val songs = listOf(
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
        )
    )
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = MainColor
    ){
        SongListSection(
            songs = songs,
            onShuffleClick = {
                Log.d("ShuffleButton", "Clicked")
            },
            onSortClick = {
                Log.d("SortButton", "Clicked")
            },
            onSongClick = { songId ->
                Log.d("SongCard", "Play song id: $songId")
            }
        )
    }
}