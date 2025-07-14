package com.fakhry.lifelog.dashboard.di

import com.fakhry.lifelog.dashboard.ui.DashboardFragment
import com.fakhry.lifelog.dashboard.ui.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun initDashboardFragmentKoinModules() = loadKoinModules(listOf(
    dashboardViewModelModules
))

private val dashboardViewModelModules = module {
    scope<DashboardFragment> {
        viewModelOf(::DashboardViewModel)
    }
}
