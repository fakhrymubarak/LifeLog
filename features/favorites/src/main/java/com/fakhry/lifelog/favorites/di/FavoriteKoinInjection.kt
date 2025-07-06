package com.fakhry.lifelog.favorites.di

import com.fakhry.lifelog.favorites.ui.FavoriteFragment
import com.fakhry.lifelog.favorites.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun initFavoriteKoinInjection() {
    loadKoinModules(
        module {
            scope<FavoriteFragment> {
                viewModelOf(::FavoriteViewModel)
            }
        }
    )
}