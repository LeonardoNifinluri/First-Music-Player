package com.example.musicplayer.core.ui.navigation

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.features.favorites.presentation.ui.FavoriteScreen
import com.example.musicplayer.features.home.presentation.ui.HomeScreen
import com.example.musicplayer.features.home.presentation.viewmodel.HomeViewModel
import com.example.musicplayer.features.home.presentation.viewmodel.HomeViewModelFactory
import com.example.musicplayer.features.playlist.presentation.ui.PlaylistScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    mediaPlayer: MediaPlayer?
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Home.route,
        modifier = modifier
    ){
        composable(route = AppRoutes.Home.route){navBackStackEntry ->
            val context = LocalContext.current
            val viewModel: HomeViewModel = remember {
                val factory = HomeViewModelFactory(
                    context.contentResolver,
                    mediaPlayer,
                    context
                )
                ViewModelProvider(
                    navBackStackEntry as ViewModelStoreOwner,
                    factory
                )[HomeViewModel::class.java]
            }
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(route = AppRoutes.Favorite.route){
            FavoriteScreen(navController = navController)
        }

        composable(route = AppRoutes.Playlist.route){
            PlaylistScreen(navController = navController)
        }
    }
}