package org.mytemplatewizard.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kmpnav3.composeapp.generated.resources.Res
import kmpnav3.composeapp.generated.resources.compose_multiplatform
import org.mytemplatewizard.project.ui.ContentBlue
import org.mytemplatewizard.project.ui.ContentGreen
import org.mytemplatewizard.project.ui.theme.MyApplicationTheme

@Composable
@Preview
fun App() {
    MyApplicationTheme {
        Scaffold {
//            ScaffoldContent()
            Navigation3SampleContent()
        }
    }
}

@Preview
@Composable
fun ScaffoldContent() {
    var showContent by remember { mutableStateOf(false) }
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
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}

data object RouteA

data class RouteB(val id: String)

@Preview
@Composable
fun Navigation3SampleContent() {
    val backStack = remember { mutableStateListOf<Any>(RouteA) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is RouteA -> NavEntry(key) {
                    ContentGreen("Welcome to Nav3") {
                        Button(onClick = {
                            backStack.add(RouteB("123"))
                        }) {
                            Text("Click to navigate")
                        }
                    }
                }

                is RouteB -> NavEntry(key) {
                    ContentBlue("Route id: ${key.id} ")
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}