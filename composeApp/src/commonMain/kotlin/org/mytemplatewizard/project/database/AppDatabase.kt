package org.mytemplatewizard.project.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.mytemplatewizard.project.model.Fruittie


@Database(entities = [Fruittie::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fruittieDao(): FruittieDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal const val DB_FILE_NAME = "fruits.db"