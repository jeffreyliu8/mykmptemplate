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
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.painterResource
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
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.mytemplatewizard.project.ui.history.SampleHistoryScreen
import org.mytemplatewizard.project.ui.home.HomePane
import org.mytemplatewizard.project.ui.settings.SampleSettingScreen
import org.mytemplatewizard.project.viewmodel.MainViewModel

@Composable
@Preview
fun App() {
    MyApplicationTheme {
//        OldDefaultComposeContent()
        MainScreen()
    }
}

@Preview
@Composable
private fun OldDefaultComposeContent() {
    var showContent by rememberSaveable { mutableStateOf(false) }
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
            val greeting = rememberSaveable { Greeting().greet() }
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
    val route: NavKey,
) {
    HOME(Res.string.main_screen_home, Icons.Default.Home, RouteA),
    HISTORY(Res.string.main_screen_history, Icons.Default.DateRange, RouteB),
    SETTINGS(Res.string.main_screen_settings, Icons.Default.Settings, RouteC),
}


@Serializable
private data object RouteA : NavKey

@Serializable
private data object RouteB : NavKey

@Serializable
private data object RouteC : NavKey

private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(RouteA::class, RouteA.serializer())
            subclass(RouteB::class, RouteB.serializer())
            subclass(RouteC::class, RouteC.serializer())
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val topLevelBackStack = rememberNavBackStack(config, RouteA)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )

//    val adaptiveInfo = currentWindowAdaptiveInfo()
//    val customNavSuiteType = with(adaptiveInfo) {
//        if (windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_EXPANDED_LOWER_BOUND)) {
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
                    selected = it.route == topLevelBackStack.lastOrNull(),
                    onClick = { topLevelBackStack.add(it.route) },
                    colors = myNavigationSuiteItemColors,
                )
            }
        }
    ) {
        NavDisplay(
            backStack = topLevelBackStack,
            onBack = { topLevelBackStack.removeLastOrNull() },
            // In order to add the `ViewModelStoreNavEntryDecorator` (see comment below for why)
            // we also need to add the default `NavEntryDecorator`s as well. These provide
            // extra information to the entry's content to enable it to display correctly
            // and save its state.
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<RouteA> {
                    HomePane()
                }
                entry<RouteB> {
                    SampleHistoryScreen()
                }
                entry<RouteC> {
                    SampleSettingScreen()
                }
            },
        )
    }
}

//https://github.com/terrakok/nav3-recipes/blob/6c7590c980898fe269484f6fda3800c45c46d7ee/composeApp/src/commonMain/kotlin/org/company/app/commonui/CommonUiCase.kt