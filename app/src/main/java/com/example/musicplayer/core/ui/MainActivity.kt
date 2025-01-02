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
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.musicplayer.core.MusicDatabase
import com.example.musicplayer.core.service.MusicService
import com.example.musicplayer.ui.theme.MainColor

class MainActivity : ComponentActivity() {

    private lateinit var database: MusicDatabase

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        database = Room.databaseBuilder(
            applicationContext,
            MusicDatabase::class.java,
            name = "music_app_database"
        ).build()

        val favoriteDao = database.favoriteDao()

        val musicService = MusicService(
            context = this,
            favoriteDao = favoriteDao
        )

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
            MusicPlayerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MainColor
                ) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        musicService = musicService
                    )
                }
            }
        }
    }
}
