package com.fakhry.lifelog.ui.fragments.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.data.Repository
import com.fakhry.lifelog.data.local.entities.NoteEntity
import kotlinx.coroutines.launch

class DashboardViewModel(private val mRepository: Repository) : ViewModel() {

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