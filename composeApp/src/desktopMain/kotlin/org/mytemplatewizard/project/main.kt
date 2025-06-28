package org.mytemplatewizard.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpNav3",
    ) {
        App()
    }
}