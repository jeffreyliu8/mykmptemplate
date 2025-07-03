package org.mytemplatewizard.project.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.mytemplatewizard.project.database.AppDatabase
import org.mytemplatewizard.project.database.DB_FILE_NAME
import java.io.File

actual class Factory() {
    actual fun createRoomDatabase(): AppDatabase {
        val dbFile = File(System.getProperty("java.io.tmpdir"), DB_FILE_NAME)
        return Room.databaseBuilder<AppDatabase>(
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