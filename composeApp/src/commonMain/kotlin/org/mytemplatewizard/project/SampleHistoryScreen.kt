package org.mytemplatewizard.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SampleHistoryScreen(
    onBackPress: () -> Unit = {},
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    BackHandler { onBackPress() }

    Scaffold { innerPadding ->
        Column {
            Button(
                onClick = { onBackPress() },
                modifier = Modifier.padding(innerPadding)
            ) {
                Text("Back to Home")
            }
            SetupNavGraph(navController = navController)
        }
    }
}