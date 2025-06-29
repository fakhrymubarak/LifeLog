package com.fakhry.lifelog.initializer

import android.content.Context
import androidx.startup.Initializer

class BaseInitializer : Initializer<Unit> {
    override fun create(context: Context) {}

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(
            KoinInitializer::class.java
        )
}
