package org.mytemplatewizard.project.ui.history

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.mytemplatewizard.project.viewmodel.DetailViewModel
import org.mytemplatewizard.project.viewmodel.HomeViewModel

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home")
    data object Detail: Screen(route = "detail")
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Home.route) {
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                navigateToDetails = {
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(route = Screen.Detail.route) {
            val viewModel = koinViewModel<DetailViewModel>()
            DetailScreen(
                navigateToHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}