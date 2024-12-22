package com.example.musicplayer.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.musicplayer.core.ui.navigation.NavGraph
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import android.Manifest
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.musicplayer.ui.theme.MainColor

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
                0
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                0
            )
        }


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mediaPlayer by remember { mutableStateOf<MediaPlayer?>(value = null) }

            MusicPlayerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MainColor
                ) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        mediaPlayer = mediaPlayer
                    )
                }
            }
        }
    }
}
