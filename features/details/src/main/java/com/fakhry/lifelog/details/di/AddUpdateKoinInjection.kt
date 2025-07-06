package com.fakhry.lifelog.details.di

import com.fakhry.lifelog.details.ui.edit.AddUpdateActivity
import com.fakhry.lifelog.details.ui.edit.AddUpdateViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun initAddUpdateKoinInjection() = loadKoinModules(
    module {
        scope<AddUpdateActivity> {
            viewModelOf(::AddUpdateViewModel)
        }
    }
)
