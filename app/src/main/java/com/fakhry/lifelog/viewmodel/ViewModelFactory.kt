package com.fakhry.lifelog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.di.Injection
import com.fakhry.lifelog.ui.activities.edit.AddUpdateViewModel
import com.fakhry.lifelog.ui.activities.read.ReadViewModel

class ViewModelFactory private constructor(private val mRepository: DataSource) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddUpdateViewModel::class.java) -> {
                AddUpdateViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(ReadViewModel::class.java) -> {
                ReadViewModel(mRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}