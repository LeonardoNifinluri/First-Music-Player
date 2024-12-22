package com.example.musicplayer.features.home.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.ui.theme.ButtonColor
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun ButtonSelectionSection(
    onFavoriteClick: () -> Unit,
    onPlaylistClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MainColor
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            CustomButton(
                text = "Favorite",
                onClick = onFavoriteClick
            )
            CustomButton(
                text = "Playlist",
                onClick = onPlaylistClick
            )
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(100.dp),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonSelectionSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MainColor
    ) {
        ButtonSelectionSection(
            onFavoriteClick = {
                Log.d("FavoriteButton", "Clicked")
            },
            onPlaylistClick = {
                Log.d("PlaylistButton", "Clicked")
            }
        )
    }
}