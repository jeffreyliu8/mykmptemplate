package org.mytemplatewizard.project.ui.home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
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