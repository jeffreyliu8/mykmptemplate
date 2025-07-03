package org.mytemplatewizard.project.di

import org.mytemplatewizard.project.database.AppDatabase

import android.app.Application
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.mytemplatewizard.project.database.DB_FILE_NAME

actual class Factory(
    private val app: Application,
) {
    actual fun createRoomDatabase(): AppDatabase {
        val dbFile = app.getDatabasePath(DB_FILE_NAME)
        return Room
            .databaseBuilder<AppDatabase>(
                context = app,
                name = dbFile.absolutePath,
            ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

//    actual fun createCartDataStore(): CartDataStore =
//        CartDataStore {
//            app.filesDir
//                .resolve(
//                    "cart.json",
//                ).absolutePath
//        }
}