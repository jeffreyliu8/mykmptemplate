package org.mytemplatewizard.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform