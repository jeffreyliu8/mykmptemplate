package org.mytemplatewizard.project.di

import org.mytemplatewizard.project.database.AppDatabase

expect class Factory {
    fun createRoomDatabase(): AppDatabase

//    fun createApi(): FruittieApi
//
//    fun createCartDataStore(): CartDataStore
}