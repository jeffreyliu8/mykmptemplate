package org.mytemplatewizard.project.di

import androidx.preference.PreferenceManager
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<Settings> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(androidContext())
        SharedPreferencesSettings(sharedPrefs)
    }
}