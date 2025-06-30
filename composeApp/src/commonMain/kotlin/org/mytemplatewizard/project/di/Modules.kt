package org.mytemplatewizard.project.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.mytemplatewizard.project.repository.LoggerRepository
import org.mytemplatewizard.project.repository.LoggerRepositoryImpl
import org.mytemplatewizard.project.repository.SampleKtorRepository
import org.mytemplatewizard.project.repository.SampleKtorRepositoryImpl
import org.mytemplatewizard.project.repository.SampleRepository
import org.mytemplatewizard.project.repository.SampleRepositoryImpl
import org.mytemplatewizard.project.viewmodel.DetailViewModel
import org.mytemplatewizard.project.viewmodel.HomePaneViewModel
import org.mytemplatewizard.project.viewmodel.HomeViewModel
import org.mytemplatewizard.project.viewmodel.MainViewModel

//expect val platformModule: Module

val sharedModule = module {
//    singleOf(::AlarmControlRepository)
//    single<FirebaseDatabaseRepository> { FirebaseDatabaseRepositoryImpl() }
//    single<DeviceActionRepository> { DeviceActionRepositoryImpl(get(), get(),get()) }
    single<SampleRepository> { SampleRepositoryImpl() }
    single<LoggerRepository> { LoggerRepositoryImpl() }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.v(message, null, "ktor")
                    }
                }
                level = LogLevel.HEADERS
            }
        }
    }
    single<SampleKtorRepository> { SampleKtorRepositoryImpl(get(), get()) }
    viewModelOf(::HomePaneViewModel)
    viewModelOf(::MainViewModel)

    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}