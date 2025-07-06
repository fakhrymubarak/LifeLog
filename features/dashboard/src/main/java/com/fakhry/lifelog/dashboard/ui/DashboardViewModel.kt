package com.fakhry.lifelog.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.commons.data.local.LocalDataRepository
import com.fakhry.lifelog.core.database.model.NoteEntity
import kotlinx.coroutines.launch

class DashboardViewModel(private val mRepository: LocalDataRepository) : ViewModel() {

    fun getAllDates(): LiveData<List<String>> {
        val listDates = MutableLiveData<List<String>>()
        viewModelScope.launch {
            listDates.postValue(mRepository.getAllDate())
        }
        return listDates
    }

    fun getNoteBasedDate(dateCreated: String): LiveData<List<NoteEntity>> {
        val listDates = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch {
            listDates.postValue(mRepository.getNotesBasedDate(dateCreated))
        }
        return listDates
    }
}