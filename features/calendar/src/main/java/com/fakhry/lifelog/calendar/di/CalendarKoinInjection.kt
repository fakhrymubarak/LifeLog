package com.fakhry.lifelog.calendar.di

import com.fakhry.lifelog.calendar.ui.CalendarFragment
import com.fakhry.lifelog.calendar.ui.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun initCalendarKoinInjection() {
    loadKoinModules(
        module {
            scope<CalendarFragment> {
                viewModelOf(::CalendarViewModel)
            }
        }
    )
}