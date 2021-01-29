package com.fakhry.lifelog.ui.fragments.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.data.Repository
import com.fakhry.lifelog.data.local.entities.NoteEntity
import kotlinx.coroutines.launch

class CalendarViewModel(private val mRepository: Repository) : ViewModel() {

    fun getNoteBasedDate(dateCreated: String): LiveData<List<NoteEntity>> {
        val listDates = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch {
            listDates.postValue(mRepository.getNotesBasedDate(dateCreated))
        }
        return listDates
    }
}