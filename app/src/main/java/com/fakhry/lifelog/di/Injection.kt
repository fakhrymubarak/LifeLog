package com.fakhry.lifelog.di

import android.content.Context
import com.fakhry.lifelog.core.data.repository.Repository
import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.storage.room.LocalDataSource
import com.fakhry.lifelog.storage.room.LifeLogDatabase

object Injection {
    fun provideRepository(context: Context): DataSource {
        val database = LifeLogDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.lifeLogDao())

        return Repository.getInstance(localDataSource)
    }
}