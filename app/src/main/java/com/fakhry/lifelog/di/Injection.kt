package com.fakhry.lifelog.di

import android.content.Context
import com.fakhry.lifelog.data.Repository
import com.fakhry.lifelog.data.local.LocalDataSource
import com.fakhry.lifelog.data.local.room.LifeLogDatabase

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = LifeLogDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.lifeLogDao())

        return Repository.getInstance(localDataSource)
    }
}