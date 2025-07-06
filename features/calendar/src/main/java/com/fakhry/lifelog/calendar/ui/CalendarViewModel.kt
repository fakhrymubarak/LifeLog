package com.fakhry.lifelog.calendar.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.commons.data.local.LocalDataRepository
import com.fakhry.lifelog.core.database.model.NoteEntity
import kotlinx.coroutines.launch

class CalendarViewModel(private val mRepository: LocalDataRepository) : ViewModel() {

    fun getNoteBasedDate(dateCreated: String): LiveData<List<NoteEntity>> {
        val listDates = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch {
            listDates.postValue(mRepository.getNotesBasedDate(dateCreated))
        }
        return listDates
    }
}