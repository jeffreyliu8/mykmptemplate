package org.mytemplatewizard.project.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.mytemplatewizard.project.database.AppDatabase

actual val platformModule: Module = module {
    single<AppDatabase?> { Factory(get()).createRoomDatabase() }
}