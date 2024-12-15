package com.fakhry.lifelog.calendar.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.core.data.repository.Repository
import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.room.LifeLogDatabase
import com.fakhry.lifelog.storage.room.LocalDataSource
import kotlinx.coroutines.launch

class CalendarViewModel(private val mRepository: DataSource) : ViewModel() {

    fun getNoteBasedDate(dateCreated: String): LiveData<List<NoteEntity>> {
        val listDates = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch {
            listDates.postValue(mRepository.getNotesBasedDate(dateCreated))
        }
        return listDates
    }


    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val database = LifeLogDatabase.getInstance(context)
                    val localDataSource = LocalDataSource.getInstance(database.lifeLogDao())
                    val repository = Repository.getInstance(localDataSource)

                    if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return CalendarViewModel(repository) as T
                    } else {
                        throw IllegalArgumentException("Unknown ViewModel class")
                    }
                }
            }
        }
    }
}