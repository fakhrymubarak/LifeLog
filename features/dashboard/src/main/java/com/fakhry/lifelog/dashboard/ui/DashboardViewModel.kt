package com.fakhry.lifelog.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.repository.NoteLocalRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repos: NoteLocalRepository) : ViewModel() {

    fun getAllDates(): LiveData<List<String>> {
        val listDates = MutableLiveData<List<String>>()
        viewModelScope.launch {
            listDates.postValue(repos.getAllDate())
        }
        return listDates
    }

    fun getNoteBasedDate(dateCreated: String): LiveData<List<NoteDomain>> {
        val listDates = MutableLiveData<List<NoteDomain>>()
        viewModelScope.launch {
            listDates.postValue(repos.getNotesBasedDate(dateCreated))
        }
        return listDates
    }
}