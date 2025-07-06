package com.fakhry.lifelog.commons.data.di

import com.fakhry.lifelog.commons.data.local.LocalDataRepository
import com.fakhry.lifelog.commons.data.local.LocalDataRepositoryImpl
import org.koin.dsl.module

fun lifeLogLocalDataReposModule() = module {
    single<LocalDataRepository> { LocalDataRepositoryImpl(get()) }
}