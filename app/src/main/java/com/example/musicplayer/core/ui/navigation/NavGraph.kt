package com.example.musicplayer.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.core.service.MusicService
import com.example.musicplayer.features.favorites.presentation.ui.FavoriteScreen
import com.example.musicplayer.features.favorites.presentation.viewmodel.FavoriteViewModel
import com.example.musicplayer.features.favorites.presentation.viewmodel.FavoriteViewModelFactory
import com.example.musicplayer.features.home.presentation.ui.HomeScreen
import com.example.musicplayer.features.home.presentation.viewmodel.HomeViewModel
import com.example.musicplayer.features.home.presentation.viewmodel.HomeViewModelFactory
import com.example.musicplayer.features.playlist.presentation.ui.PlaylistScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    musicService: MusicService
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Home.route,
        modifier = modifier
    ){
        composable(route = AppRoutes.Home.route){navBackStackEntry ->
            val viewModel: HomeViewModel = remember {
                val factory = HomeViewModelFactory(musicService)
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

        composable(route = AppRoutes.Favorite.route){navBackStackEntry ->
            val viewModel: FavoriteViewModel = remember {
                val factory = FavoriteViewModelFactory(musicService)
                ViewModelProvider(
                    navBackStackEntry as ViewModelStoreOwner,
                    factory
                )[FavoriteViewModel::class.java]
            }
            FavoriteScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(route = AppRoutes.Playlist.route){
            PlaylistScreen(navController = navController)
        }
    }
}