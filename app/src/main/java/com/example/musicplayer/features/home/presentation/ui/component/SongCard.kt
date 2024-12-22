package com.example.musicplayer.features.home.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun SongCard(
    song: Song,
    onClick: () -> Unit
) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MainColor
        )
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column (
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = song.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = song.id.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSongCard() {
    Surface(
        color = MainColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        val song = Song(
            id = 122220233L,
            name = "Fun Song",
            uri = null
        )
        SongCard(
            song = song,
            onClick = {
                Log.d("SongCard", "Play song id: ${song.id}")
            }
        )
    }
}