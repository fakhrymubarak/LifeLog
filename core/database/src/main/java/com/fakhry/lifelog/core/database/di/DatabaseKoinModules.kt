package com.fakhry.lifelog.core.database.di

import com.fakhry.lifelog.core.database.room.LifeLogDao
import com.fakhry.lifelog.core.database.room.LifeLogDatabase
import org.koin.dsl.module

fun lifeLogLocalDataSourceModule() = module {
    single<LifeLogDatabase> { LifeLogDatabase.getInstance(get()) }
    single<LifeLogDao> { get<LifeLogDatabase>().lifeLogDao() }
}