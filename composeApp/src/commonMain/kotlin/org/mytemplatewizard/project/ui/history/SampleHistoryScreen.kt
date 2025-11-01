package org.mytemplatewizard.project.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SampleHistoryScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column {
            Button(
                onClick = { },
                modifier = Modifier.padding(innerPadding)
            ) {
                Text("do nothing")
            }
//            SetupNavGraph(navController = navController)
        }
    }
}