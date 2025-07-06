package com.fakhry.lifelog.commons.data.di

import com.fakhry.lifelog.commons.data.local.LocalDataRepository
import com.fakhry.lifelog.commons.data.local.LocalDataRepositoryImpl
import com.fakhry.lifelog.commons.data.mapper.NoteLocalDomainToEntityMapper
import com.fakhry.lifelog.commons.data.mapper.NoteLocalEntityToDomainMapper
import com.fakhry.lifelog.commons.data.repository.NoteLocalRepositoryImpl
import com.fakhry.lifelog.domain.repository.NoteLocalRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun lifeLogLocalDataReposModule() = module {
    // TODO REMOVE THIS AFTER MIGRATION
    single<LocalDataRepository> { LocalDataRepositoryImpl(get()) }

    singleOf(::NoteLocalDomainToEntityMapper)
    singleOf(::NoteLocalEntityToDomainMapper)
    single<NoteLocalRepository> { NoteLocalRepositoryImpl(get(), get(), get()) }
}