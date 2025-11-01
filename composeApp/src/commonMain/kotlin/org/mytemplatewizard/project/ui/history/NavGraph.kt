package org.mytemplatewizard.project.ui.history

import androidx.compose.runtime.Composable


sealed class Screen(val route: String) {
    data object Home: Screen(route = "home")
    data object Detail: Screen(route = "detail")
}

@Composable
fun SetupNavGraph(
//    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ) {
//        composable(route = Screen.Home.route) {
//            val viewModel = koinViewModel<HomeViewModel>()
//            HomeScreen(
//                navigateToDetails = {
//                    navController.navigate(Screen.Detail.route)
//                }
//            )
//        }
//        composable(route = Screen.Detail.route) {
//            val viewModel = koinViewModel<DetailViewModel>()
//            DetailScreen(
//                navigateToHome = {
//                    navController.popBackStack()
//                }
//            )
//        }
//    }
}