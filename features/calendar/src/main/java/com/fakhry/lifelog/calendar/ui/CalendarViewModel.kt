package com.fakhry.lifelog.calendar.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.repository.NoteLocalRepository
import kotlinx.coroutines.launch

class CalendarViewModel(private val repos: NoteLocalRepository) : ViewModel() {

    fun getNoteBasedDate(dateCreated: String): LiveData<List<NoteDomain>> {
        val listDates = MutableLiveData<List<NoteDomain>>()
        viewModelScope.launch {
            listDates.postValue(repos.getNotesBasedDate(dateCreated))
        }
        return listDates
    }
}