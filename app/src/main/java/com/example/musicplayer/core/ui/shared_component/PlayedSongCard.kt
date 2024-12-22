package com.example.musicplayer.core.ui.shared_component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import com.example.musicplayer.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.core.model.Song
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun PlayedSongCard(
    song: Song?,
    onPrev: () -> Unit,
    onResume: () -> Unit,
    onPause: () -> Unit,
    onNext: () -> Unit,
) {
    song?.let {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = MainColor,
            border = BorderStroke(
                width = 1.dp,
                color = Color.White
            ),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = song.name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = onPrev
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Button",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = if(song.isPlaying.value) onPause else onResume
                    ) {
                        if(song.isPlaying.value){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pause),
                                contentDescription = "Pause Button",
                                tint = Color.White
                            )
                        }else{
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play Button",
                                tint = Color.White
                            )
                        }
                    }
                    IconButton(
                        onClick = onNext
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next Button",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPlayedSongCard() {
    val song = Song(
        name = "Test Title",
        id = 1010100L,
        uri = null
    )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MainColor
    ) {
        PlayedSongCard(
            song = song,
            onPrev = {
                Log.d("PrevButton", "Clicked")
            },
            onResume = {
                Log.d("ResumeButton", "Clicked")
            },
            onPause = {
                Log.d("PauseButton", "Clicked")
            },
            onNext = {
                Log.d("NextButton", "Clicked")
            }
        )
    }
}