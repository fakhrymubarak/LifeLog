package com.fakhry.lifelog.initializer

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

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
            // ADD SINGLETON MODULE HERE
        )
    )
}
