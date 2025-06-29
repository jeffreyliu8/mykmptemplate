package org.mytemplatewizard.project


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SampleSettingScreen(
    onBackPress: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BackHandler { onBackPress() }
    Text("History Screen")
}
