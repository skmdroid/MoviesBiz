package com.skmdroid.moviesbiz.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skmdroid.moviesbiz.presentation.ui.MainScreen
import com.skmdroid.moviesbiz.presentation.ui.MovieBizScreen
import com.skmdroid.moviesbiz.presentation.ui.MovieDetailScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = MovieBizScreen.HomeScreen.name) {
        composable(MovieBizScreen.HomeScreen.name) { MainScreen(navController = navController) }
        composable(
            MovieBizScreen.MovieDetail.name + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            MovieDetailScreen(navController = navController, id = id)
        }
    }
}
