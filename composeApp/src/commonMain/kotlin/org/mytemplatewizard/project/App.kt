package org.mytemplatewizard.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kmpnav3.composeapp.generated.resources.Res
import kmpnav3.composeapp.generated.resources.compose_multiplatform
import kmpnav3.composeapp.generated.resources.main_screen_history
import kmpnav3.composeapp.generated.resources.main_screen_home
import kmpnav3.composeapp.generated.resources.main_screen_settings
import org.jetbrains.compose.resources.StringResource
import org.mytemplatewizard.project.ui.theme.MyApplicationTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.mytemplatewizard.project.viewmodel.HomePaneViewModel
import org.mytemplatewizard.project.viewmodel.MainViewModel

@Composable
@Preview
fun App() {
    MyApplicationTheme {
        Scaffold {
//            ScaffoldContent()
            MainScreen()
        }
    }
}

@Preview
@Composable
fun ScaffoldContent() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}

enum class AppDestinations(
    val label: StringResource,
    val icon: ImageVector,
) {
    HOME(Res.string.main_screen_home, Icons.Default.Home),
    HISTORY(Res.string.main_screen_history, Icons.Default.DateRange),
    SETTINGS(Res.string.main_screen_settings, Icons.Default.Settings),
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )

//    val adaptiveInfo = currentWindowAdaptiveInfo()
//    val customNavSuiteType = with(adaptiveInfo) {
//        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
//            NavigationSuiteType.NavigationDrawer
//        } else {
//            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
//        }
//    }

    NavigationSuiteScaffold(
//        layoutType = customNavSuiteType,
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = stringResource(it.label),
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                    colors = myNavigationSuiteItemColors,
                )
            }
        }
    ) {
        val historyNavController = rememberNavController()
        // Destination content.
        when (currentDestination) {
            AppDestinations.HOME -> HomePane()
            AppDestinations.HISTORY -> SampleHistoryScreen(
                onBackPress = { currentDestination = AppDestinations.HOME },
                navController = historyNavController,
            )

            AppDestinations.SETTINGS -> SampleSettingScreen(
                onBackPress = { currentDestination = AppDestinations.HOME },
            )
        }
    }
}



@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
@Preview
fun HomePane(
    viewModel: HomePaneViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<String>()
    val selectedItemKey = scaffoldNavigator.currentDestination?.contentKey

    Scaffold { innerPadding ->
        ListDetailPaneScaffold(
            directive = scaffoldNavigator.scaffoldDirective,
            value = scaffoldNavigator.scaffoldValue,
            listPane = {
                AnimatedPane(
                    modifier = Modifier.preferredWidth(600.dp)
                ) {
                    Text("home listPane ${uiState.sampleInt}")
                }
            },
            detailPane = {
                if (selectedItemKey != null) {
                    AnimatedPane(modifier = Modifier) {
                        Text("home detailPane ${uiState.sampleInt}")
                    }
                }
            },
        )
    }
}