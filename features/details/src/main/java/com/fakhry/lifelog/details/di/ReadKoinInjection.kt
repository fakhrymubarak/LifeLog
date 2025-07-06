package com.fakhry.lifelog.details.di

import com.fakhry.lifelog.details.ui.read.ReadActivity
import com.fakhry.lifelog.details.ui.read.ReadViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun initReadKoinInjection() = loadKoinModules(
    module {
        scope<ReadActivity> {
            viewModelOf(::ReadViewModel)
        }
    }
)