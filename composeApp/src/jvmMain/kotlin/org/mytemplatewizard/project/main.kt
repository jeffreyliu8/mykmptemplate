package org.mytemplatewizard.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.mytemplatewizard.project.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpNav3",
    ) {
        initKoin()
        App()
    }
}