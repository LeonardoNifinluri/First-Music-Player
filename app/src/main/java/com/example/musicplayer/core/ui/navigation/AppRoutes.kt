package com.example.musicplayer.core.ui.navigation

sealed class AppRoutes(val route: String){
    data object Home: AppRoutes(route = "home")
    data object Favorite: AppRoutes(route = "favorite")
    data object Playlist: AppRoutes(route = "playlist")
}