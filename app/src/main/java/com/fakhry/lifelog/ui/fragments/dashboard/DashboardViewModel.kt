package com.fakhry.lifelog.ui.fragments.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.core.data.repository.Repository
import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.storage.model.NoteEntity
import kotlinx.coroutines.launch

class DashboardViewModel(private val mRepository: DataSource) : ViewModel() {

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