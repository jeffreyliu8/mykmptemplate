package org.mytemplatewizard.project.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.mytemplatewizard.project.repository.LoggerRepository
import org.mytemplatewizard.project.repository.LoggerRepositoryImpl
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
    viewModelOf(::HomePaneViewModel)
    viewModelOf(::MainViewModel)

    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}