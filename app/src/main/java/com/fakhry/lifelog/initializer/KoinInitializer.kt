package com.fakhry.lifelog.initializer

import android.content.Context
import androidx.startup.Initializer
import com.fakhry.lifelog.commons.data.di.lifeLogLocalDataReposModule
import com.fakhry.lifelog.core.database.di.lifeLogLocalDataSourceModule
import com.fakhry.lifelog.utils.coroutines.DispatcherProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context.applicationContext)
        }
        loadSingleModules()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    private fun loadSingleModules() = loadKoinModules(
        listOf(
            provideDispatcherProvider(),
            lifeLogLocalDataSourceModule(),
            lifeLogLocalDataReposModule(),
        )
    )

    private fun provideDispatcherProvider() = module {
        single { DispatcherProvider() }
    }
}
