package org.mytemplatewizard.project.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationEventHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.mytemplatewizard.project.viewmodel.HomePaneViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
@Preview
fun HomePane(
    viewModel: HomePaneViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<String>()
    val selectedItemKey = scaffoldNavigator.currentDestination?.contentKey
    val scope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        ListDetailPaneScaffold(
            directive = scaffoldNavigator.scaffoldDirective,
            value = scaffoldNavigator.scaffoldValue,
            listPane = {
                AnimatedPane(
                    modifier = Modifier.preferredWidth(600.dp)
                ) {
                    LazyColumn(
                        contentPadding = innerPadding
                    ) {
                        items(100) { index ->
                            Button(onClick = {
                                scope.launch {
                                    scaffoldNavigator.navigateTo(
                                        ListDetailPaneScaffoldRole.Detail, index.toString()
                                    )
                                }
                            }) {
                                Text("Item $index")
                            }
                        }
                    }
                }
            },
            detailPane = {
                if (selectedItemKey != null) {
                    AnimatedPane(modifier = Modifier) {
                        NavigationEventHandler(
                            state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
                            isBackEnabled = scaffoldNavigator.currentDestination?.pane == ListDetailPaneScaffoldRole.Detail,
                        ) {
                            scope.launch {
                                scaffoldNavigator.navigateTo(
                                    ListDetailPaneScaffoldRole.List, null
                                )
                            }
                        }
                        LazyColumn(
                            contentPadding = innerPadding
                        ) {
                            item {
                                Text("home detailPane counting number ${uiState.sampleInt}")
                            }
                            item {
                                Text("home detailPane $selectedItemKey")
                            }
                        }
                    }
                }
            },
        )
    }
}