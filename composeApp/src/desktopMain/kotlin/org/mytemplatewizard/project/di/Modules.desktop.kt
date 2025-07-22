package org.mytemplatewizard.project.di

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.prefs.Preferences

actual val platformModule: Module = module {
    single<Settings> {
        val preferences = Preferences.userRoot()
        PreferencesSettings(preferences)
    }
}