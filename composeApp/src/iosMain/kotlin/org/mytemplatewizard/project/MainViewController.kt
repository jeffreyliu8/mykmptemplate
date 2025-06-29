package org.mytemplatewizard.project

import androidx.compose.ui.window.ComposeUIViewController
import org.mytemplatewizard.project.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }