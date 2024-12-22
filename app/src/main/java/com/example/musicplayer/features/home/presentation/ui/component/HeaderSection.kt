package com.example.musicplayer.features.home.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.musicplayer.ui.theme.MainColor

@Composable
fun HeaderSection(
    onSearchClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MainColor
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Music Player",
                modifier = Modifier.weight(1f),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MainColor
    ) {
        HeaderSection(
            onSearchClick = {
                Log.d("SearchButton", "Pressed")
            }
        )
    }
}