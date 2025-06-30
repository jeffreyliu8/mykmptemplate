package org.mytemplatewizard.project.ui.settings


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.mytemplatewizard.project.ui.misc.BackHandler


@Composable
fun SampleSettingScreen(
    onBackPress: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    BackHandler { onBackPress() }
    Text("History Screen")
}
